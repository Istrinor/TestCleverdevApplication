package org.cleverdevtest.test.patient.service;

import lombok.extern.slf4j.Slf4j;
import org.cleverdevtest.test.note.dto.OldSystemNoteRequestDto;
import org.cleverdevtest.test.note.dto.OldSystemResponseNoteDto;
import org.cleverdevtest.test.note.model.NoteEntity;
import org.cleverdevtest.test.note.repository.NoteRepository;
import org.cleverdevtest.test.oldsystem.service.OldSystemImportService;
import org.cleverdevtest.test.patient.dto.OldSystemClientDto;
import org.cleverdevtest.test.patient.model.OldClientGuIdEntity;
import org.cleverdevtest.test.patient.model.PatientEntity;
import org.cleverdevtest.test.patient.model.enums.PatientSecondLevelStatus;
import org.cleverdevtest.test.patient.repository.OldClientGuIdRepository;
import org.cleverdevtest.test.user.model.CompanyUserEntity;
import org.cleverdevtest.test.user.repository.CompanyUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientService {
    private final OldSystemImportService oldSystemImportService;
    private final NoteRepository noteRepository;
    private final OldClientGuIdRepository oldClientGuIdRepository;
    private final CompanyUserRepository companyUserRepository;

    public PatientService(OldSystemImportService oldSystemImportService, NoteRepository noteRepository,
                          OldClientGuIdRepository oldClientGuIdRepository, CompanyUserRepository companyUserRepository) {
        this.oldSystemImportService = oldSystemImportService;
        this.noteRepository = noteRepository;
        this.oldClientGuIdRepository = oldClientGuIdRepository;
        this.companyUserRepository = companyUserRepository;
    }

    public void getNotesFromOldSystemAndSaveOrUpdate() {
        List<OldSystemClientDto> oldClients = oldSystemImportService.getClients();
        prepareAndSaveNotesByOldClients(oldClients);
    }

    public void prepareAndSaveNotesByOldClients(List<OldSystemClientDto> oldClients) {
        List<String> oldClientGuids = oldClients.stream()
                .map(OldSystemClientDto::getGuid)
                .collect(Collectors.toList());
        log.info("Количество пациентов из старой системы " + oldClients.size());
        List<OldClientGuIdEntity> activePatientsOldClientGuidEntities =
                oldClientGuIdRepository.getClientGuidsOfActivePatientsByAllClientGuids(PatientSecondLevelStatus.ACTIVE.getId(),
                        oldClientGuids);
        Map<String, PatientEntity> oldSystemClientGuidToPatientCache = activePatientsOldClientGuidEntities.stream()
                .collect(Collectors.toMap(OldClientGuIdEntity::getOldClientGuid, OldClientGuIdEntity::getPatient));
        Set<String> activePatientsOldClientGuids = activePatientsOldClientGuidEntities.stream()
                .map(OldClientGuIdEntity::getOldClientGuid)
                .collect(Collectors.toSet());
        List<OldSystemClientDto> oldSystemClients = oldClients.stream()
                .filter(oldSystemClientDto -> activePatientsOldClientGuids.contains(oldSystemClientDto.getGuid()))
                .collect(Collectors.toList());
        LocalDateTime currentDateTime = LocalDateTime.now();

        NoteEntity firstNote = noteRepository.findTopByOrderByCreatedDateTimeAsc();
        LocalDate fromDate = firstNote.getCreatedDateTime().toLocalDate();
        LocalDate toDate = currentDateTime.toLocalDate();
        List<OldSystemResponseNoteDto> oldSystemNotes = oldSystemClients.stream()
                .map(oldSystemClientDto -> generateRequestAndGetOldSystemNotes(oldSystemClientDto.getAgency(), fromDate, toDate,
                        oldSystemClientDto.getGuid()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("Количество заметок полученых из старой системы за период с {} по {}: {}", fromDate, toDate, oldSystemNotes.size());
        Set<String> oldSystemNoteGuids = oldSystemNotes.stream()
                .map(OldSystemResponseNoteDto::getGuid)
                .collect(Collectors.toSet());

        List<OldSystemResponseNoteDto> processingNotes = oldSystemNotes.stream()
                .filter(oldSystemResponseNoteDto -> activePatientsOldClientGuids.contains(oldSystemResponseNoteDto.getClientGuid()))
                .collect(Collectors.toList());

        Map<String, NoteEntity> oldSystemNoteGuidToNoteEntityCache = getOldSystemNoteGuidToNoteEntityCache(oldSystemNoteGuids);
        Map<String, CompanyUserEntity> loginToUserCache = getLoginToUserCache(processingNotes);

        processAndSaveNotes(oldSystemClientGuidToPatientCache, currentDateTime, processingNotes, oldSystemNoteGuidToNoteEntityCache,
                loginToUserCache);
    }

    private void processAndSaveNotes(Map<String, PatientEntity> oldSystemClientGuidToPatient, LocalDateTime currentDateTime, List<OldSystemResponseNoteDto> processingNotes, Map<String, NoteEntity> oldSystemNoteGuidToNoteEntityCache, Map<String, CompanyUserEntity> loginToUserCache) {
        List<NoteEntity> newNotesFromOldSystem = new ArrayList<>();
        int countUpdatedNotes = 0;
        int countSavedNotes = 0;
        Set<String> existedInNewSystemOldSystemNoteGuids = oldSystemNoteGuidToNoteEntityCache.keySet();
        for (OldSystemResponseNoteDto processingNote : processingNotes) {
            String oldSystemNoteGuid = processingNote.getGuid();
            CompanyUserEntity companyUserEntity = loginToUserCache.get(processingNote.getLoggedUser());
            if (existedInNewSystemOldSystemNoteGuids.contains(oldSystemNoteGuid)) {
                NoteEntity existedNoteEntity = oldSystemNoteGuidToNoteEntityCache.get(oldSystemNoteGuid);
                if (processingNote.getModifiedDateTime().isAfter(existedNoteEntity.getLastModifiedDateTime())) {
                    existedNoteEntity.setNote(processingNote.getComments());
                    existedNoteEntity.setLastModifiedDateTime(currentDateTime);
                    existedNoteEntity.setLastModifiedByUser(companyUserEntity);
                    countUpdatedNotes++;
                }
            } else {
                NoteEntity newNoteEntity = new NoteEntity();
                newNoteEntity.setOldSystemNoteGuid(oldSystemNoteGuid);
                newNoteEntity.setCreatedDateTime(processingNote.getCreatedDateTime());
                newNoteEntity.setCreatedByUser(companyUserEntity);
                newNoteEntity.setLastModifiedDateTime(currentDateTime);
                newNoteEntity.setLastModifiedByUser(companyUserEntity);
                newNoteEntity.setNote(processingNote.getComments());
                newNoteEntity.setPatientEntity(oldSystemClientGuidToPatient.get(processingNote.getClientGuid()));
                newNotesFromOldSystem.add(newNoteEntity);
                countSavedNotes++;
            }
        }
        log.info("Количество обновленных заметок в новой системе {}", countUpdatedNotes);
        log.info("Количество сохраненных новых заметок в новой системе {}", countSavedNotes);
        noteRepository.saveAll(newNotesFromOldSystem);
    }

    private Map<String, NoteEntity> getOldSystemNoteGuidToNoteEntityCache(Set<String> oldSystemNoteGuids) {
        List<NoteEntity> existedNotesByOldSystemNoteGuids =
                noteRepository.findAllByOldSystemNoteGuidIn(oldSystemNoteGuids);
        Map<String, NoteEntity> oldSystemNoteGuidToNoteEntity = existedNotesByOldSystemNoteGuids.stream()
                .collect(Collectors.toMap(NoteEntity::getOldSystemNoteGuid, Function.identity()));
        return oldSystemNoteGuidToNoteEntity;
    }

    private Map<String, CompanyUserEntity> getLoginToUserCache(List<OldSystemResponseNoteDto> processingNotes) {
        Set<String> oldSystemLogins = processingNotes.stream()
                .map(OldSystemResponseNoteDto::getLoggedUser)
                .collect(Collectors.toSet());
        List<CompanyUserEntity> existedUsers = companyUserRepository.findAllByLoginIn(oldSystemLogins);
        Set<String> existedLogins = existedUsers.stream()
                .map(CompanyUserEntity::getLogin)
                .collect(Collectors.toSet());
        List<CompanyUserEntity> allUsers = new ArrayList<>(existedUsers);
        List<CompanyUserEntity> newUsers = new ArrayList<>();
        for (String oldSystemLogin : oldSystemLogins) {
            if (!existedLogins.contains(oldSystemLogin)) {
                CompanyUserEntity newUser = new CompanyUserEntity();
                newUser.setLogin(oldSystemLogin);
                newUsers.add(newUser);
            }
        }
        log.info("Количество новых пользователей добавленных в систему: {}", newUsers.size());
        companyUserRepository.saveAll(newUsers);
        allUsers.addAll(newUsers);

        Map<String, CompanyUserEntity> loginToUser = allUsers.stream()
                .collect(Collectors.toMap(CompanyUserEntity::getLogin, Function.identity()));
        return loginToUser;
    }

    private List<OldSystemResponseNoteDto> generateRequestAndGetOldSystemNotes(String agency, LocalDate dateFrom,
                                                                               LocalDate dateTo, String clientGuid) {
        OldSystemNoteRequestDto oldSystemNoteRequestDto = new OldSystemNoteRequestDto();
        oldSystemNoteRequestDto.setAgency(agency);

        oldSystemNoteRequestDto.setDateFrom(dateFrom);
        oldSystemNoteRequestDto.setDateTo(dateTo);
        oldSystemNoteRequestDto.setClientGuid(clientGuid);
        return oldSystemImportService.getNotes(oldSystemNoteRequestDto);
    }

}
