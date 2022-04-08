package org.cleverdevtest.test.note.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Шаблон для построения обьектов подтянутых из старой системы
 */
public class OldSystemNoteRequestDto {
    /**
     * Идентификатор для поиска заметки
     */
    private String agency;
    /**
     * Дата создания заметки, период от которого будут выводиться заметки (включительно)
     */
    private LocalDate dateFrom;
    /**
     * Дата заметки, период до которого будут выводиться заметки (включительно)
     */
    private LocalDate dateTo;
    /**
     * Идентификатор для поиска заметки
     */
    private String clientGuid;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getClientGuid() {
        return clientGuid;
    }

    public void setClientGuid(String clientGuid) {
        this.clientGuid = clientGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OldSystemNoteRequestDto that = (OldSystemNoteRequestDto) o;
        return Objects.equals(agency, that.agency) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo) &&
                Objects.equals(clientGuid, that.clientGuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agency, dateFrom, dateTo, clientGuid);
    }
}
