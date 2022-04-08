package org.cleverdevtest.test.scheduler;

import org.cleverdevtest.test.patient.service.PatientService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OldSystemImportScheduler {

    private final PatientService patientService;

    public OldSystemImportScheduler(PatientService patientService) {
        this.patientService = patientService;
    }

    @Scheduled(cron = "${importOldSystemClientsAndNotesAndUsers.cron-schedule}")
    private void importOldSystemClientsAndNotesAndUsers() {
        patientService.getNotesFromOldSystemAndSaveOrUpdate();
    }
}
