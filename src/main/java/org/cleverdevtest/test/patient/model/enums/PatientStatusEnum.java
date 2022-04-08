package org.cleverdevtest.test.patient.model.enums;

public enum PatientStatusEnum {
    FIRST(200, PatientSecondLevelStatus.ACTIVE),
    SECOND(210, PatientSecondLevelStatus.ACTIVE),
    THIRD(230, PatientSecondLevelStatus.ACTIVE);

    private final Integer id;
    private final PatientSecondLevelStatus patientSecondLevelStatus;

    PatientStatusEnum(Integer id, PatientSecondLevelStatus patientSecondLevelStatus) {
        this.id = id;
        this.patientSecondLevelStatus = patientSecondLevelStatus;
    }

    public Integer getId() {
        return id;
    }
}
