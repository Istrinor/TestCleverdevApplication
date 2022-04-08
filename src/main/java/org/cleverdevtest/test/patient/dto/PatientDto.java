package org.cleverdevtest.test.patient.dto;

import java.util.Objects;

/**
 * Преобразование обекта типа "Client"  к новой системе
 */
public class PatientDto {
    /**
     * Имя
     */
    private String firstName;
    /**
     * Фамилия
     */
    private String lastName;
    /**
     * Идентификатор клиента из старой базы данных
     */
    private String oldClientGuId;
    /**
     * Статус пользователя
     */
    private Integer status;

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

    public String getOldClientGuId() {
        return oldClientGuId;
    }

    public void setOldClientGuId(String oldClientGuId) {
        this.oldClientGuId = oldClientGuId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDto that = (PatientDto) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(oldClientGuId, that.oldClientGuId) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, oldClientGuId, status);
    }
}
