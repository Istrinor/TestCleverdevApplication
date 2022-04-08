package org.cleverdevtest.test.oldsystem.service;

import org.cleverdevtest.test.note.repository.NoteRepository;
import org.cleverdevtest.test.patient.dto.OldSystemClientDto;
import org.cleverdevtest.test.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OldSystemImportServiceTest {

    @Autowired
    private PatientService patientService;
    @Autowired
    private NoteRepository noteRepository;


    @Test
    void testGetNotesFromOldSystemAndSaveOrUpdate() {
        List<OldSystemClientDto> oldClients = prepareOldClientsArgument();
        patientService.prepareAndSaveNotesByOldClients(oldClients);
    }

    private List<OldSystemClientDto> prepareOldClientsArgument() {
        List<OldSystemClientDto> oldClients = new ArrayList<>();
        OldSystemClientDto oldSystemClientDto = new OldSystemClientDto();
        oldSystemClientDto.setAgency("v01");
        oldSystemClientDto.setCreatedDateTime(LocalDateTime.parse("2021-10-20T03:10:47"));
        oldSystemClientDto.setGuid("A661E400-8CD0-6336-D0C2-2E8012903819");
        oldClients.add(oldSystemClientDto);
        return oldClients;
    }

}