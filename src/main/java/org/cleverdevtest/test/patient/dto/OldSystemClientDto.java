package org.cleverdevtest.test.patient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.cleverdevtest.test.patient.model.enums.OldSystemClientStatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class OldSystemClientDto {
    /**
     * Идентификатор, соответствующий записям OldSystemNoteRepositoryDto.agency
     */
    private String agency;
    /**
     * Идентификатор, соответствующий записям OldSystemNoteRepositoryDto.clientGuId
     */
    private String guid;
    /**
     * Имя клиента
     */
    private String firstName;
    /**
     * Фамилия клиента
     */
    private String lastName;
    /**
     * Статус клиента
     */
    private OldSystemClientStatusEnum status;
    /**
     * День рождения клиента
     */
    private LocalDate dob;
    /**
     * Дата создания клиента в старой системе
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDateTime;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public OldSystemClientStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OldSystemClientStatusEnum status) {
        this.status = status;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OldSystemClientDto that = (OldSystemClientDto) o;
        return Objects.equals(agency, that.agency) &&
                Objects.equals(guid, that.guid) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                status == that.status &&
                Objects.equals(dob, that.dob) &&
                Objects.equals(createdDateTime, that.createdDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agency, guid, firstName, lastName, status, dob, createdDateTime);
    }
}
