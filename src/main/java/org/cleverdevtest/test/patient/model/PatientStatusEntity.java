package org.cleverdevtest.test.patient.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient_status")
public class PatientStatusEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @JoinColumn(name = "second_level_status_id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SecondLevelStatusEntity secondLevelStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SecondLevelStatusEntity getSecondLevelStatus() {
        return secondLevelStatus;
    }

    public void setSecondLevelStatus(SecondLevelStatusEntity secondLevelStatusEntity) {
        this.secondLevelStatus = secondLevelStatusEntity;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!getClass().isInstance(o)) {
            return false;
        }
        PatientStatusEntity other = (PatientStatusEntity) o;
        return this.id != null && Objects.equals(this.id, other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
