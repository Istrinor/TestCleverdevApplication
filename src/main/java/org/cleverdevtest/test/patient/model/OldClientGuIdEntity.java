package org.cleverdevtest.test.patient.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "old_client_guid")
public class OldClientGuIdEntity {

    @Id
    @Column(name = "old_client_guid", nullable = false, updatable = false)
    private String oldClientGuid;

    @JoinColumn(name = "patient_id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PatientEntity patient;

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public String getOldClientGuid() {
        return oldClientGuid;
    }

    public void setOldClientGuid(String oldClientGuid) {
        this.oldClientGuid = oldClientGuid;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!getClass().isInstance(o)) {
            return false;
        }
        OldClientGuIdEntity other = (OldClientGuIdEntity) o;
        return this.oldClientGuid != null && Objects.equals(this.oldClientGuid, other.oldClientGuid);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
