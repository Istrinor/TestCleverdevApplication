package org.cleverdevtest.test.patient.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность пациента выводимого в новой системе
 */
@Entity
@Table(name = "patient_profile")
public class PatientEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @JoinColumn(name = "patient_status_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PatientStatusEntity patientStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PatientStatusEntity getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(PatientStatusEntity patientStatus) {
        this.patientStatus = patientStatus;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!getClass().isInstance(o)) {
            return false;
        }
        PatientEntity other = (PatientEntity) o;
        return this.id != null && Objects.equals(this.id, other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
