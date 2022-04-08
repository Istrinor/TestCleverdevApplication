package org.cleverdevtest.test.oldsystem.service;

import org.cleverdevtest.test.note.dto.OldSystemNoteRequestDto;
import org.cleverdevtest.test.note.dto.OldSystemResponseNoteDto;
import org.cleverdevtest.test.patient.dto.OldSystemClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Сервис используется для получения данных старой системы
 */
@Service
public class OldSystemImportService {
    @Value("${oldSystem.url}")
    private String oldSystemUrl;
    private final RestTemplate restTemplate;

    public OldSystemImportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<OldSystemClientDto> getClients() {
        String clientsUrl = oldSystemUrl + "/clients";
        OldSystemClientDto[] clients = restTemplate.postForObject(clientsUrl, null, OldSystemClientDto[].class);
        Objects.requireNonNull(clients);
        return Arrays.asList(clients);
    }

    public List<OldSystemResponseNoteDto> getNotes(OldSystemNoteRequestDto clientNoteRequestDto) {
        String notesUrl = oldSystemUrl + "/notes";
        OldSystemResponseNoteDto[] notes = restTemplate.postForObject(notesUrl, clientNoteRequestDto, OldSystemResponseNoteDto[].class);
        Objects.requireNonNull(notes);
        return Arrays.asList(notes);
    }

}
