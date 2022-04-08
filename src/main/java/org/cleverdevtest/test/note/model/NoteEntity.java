package org.cleverdevtest.test.note.model;

import org.cleverdevtest.test.patient.model.PatientEntity;
import org.cleverdevtest.test.user.model.CompanyUserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Сущность заметки выводимой в новой системе
 */
@Entity
@Table(name = "patient_note")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "old_system_note_guid", updatable = false)
    private String oldSystemNoteGuid;
    @Column(name = "created_date_time", nullable = false, updatable = false)
    private LocalDateTime createdDateTime;
    @Column(name = "last_modified_date_time", nullable = false)
    private LocalDateTime lastModifiedDateTime;
    @JoinColumn(name = "created_by_user_id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CompanyUserEntity createdByUser;
    @JoinColumn(name = "last_modified_by_user_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CompanyUserEntity lastModifiedByUser;
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "pateint_id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PatientEntity patientEntity;

    public String getOldSystemNoteGuid() {
        return oldSystemNoteGuid;
    }

    public void setOldSystemNoteGuid(String oldSystemNoteGuid) {
        this.oldSystemNoteGuid = oldSystemNoteGuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public CompanyUserEntity getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(CompanyUserEntity createdByUser) {
        this.createdByUser = createdByUser;
    }

    public CompanyUserEntity getLastModifiedByUser() {
        return lastModifiedByUser;
    }

    public void setLastModifiedByUser(CompanyUserEntity lastModifiedByUserId) {
        this.lastModifiedByUser = lastModifiedByUserId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(PatientEntity patientEntity) {
        this.patientEntity = patientEntity;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!getClass().isInstance(o)) {
            return false;
        }
        NoteEntity other = (NoteEntity) o;
        return this.id != null && Objects.equals(this.id, other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
