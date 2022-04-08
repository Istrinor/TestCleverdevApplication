package org.cleverdevtest.test.patient.model.enums;

public enum PatientSecondLevelStatus {
    ACTIVE(1),
    INACTIVE(2),
    PENDING(3);

    private final Integer id;

    PatientSecondLevelStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
