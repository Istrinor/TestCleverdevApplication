package org.cleverdevtest.test.note.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Объект запроса на получение данных об "Note"  из старой системы
 */
public class OldSystemResponseNoteDto {
    /**
     * Содержание заметки
     */
    private String comments;
    /**
     * Идентификатор заметки
     */
    private String guid;
    /**
     * Дата последнего изменения заметки
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDateTime;
    /**
     * Идентификатор клиента
     */
    private String clientGuid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /**
     * Дата создания заметки
     */
    private LocalDateTime createdDateTime;
    /**
     * Логин клиента
     */
    private String loggedUser;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public LocalDateTime getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getClientGuid() {
        return clientGuid;
    }

    public void setClientGuid(String clientGuid) {
        this.clientGuid = clientGuid;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OldSystemResponseNoteDto that = (OldSystemResponseNoteDto) o;
        return Objects.equals(comments, that.comments) &&
                Objects.equals(guid, that.guid) &&
                Objects.equals(modifiedDateTime, that.modifiedDateTime) &&
                Objects.equals(clientGuid, that.clientGuid) &&
                Objects.equals(createdDateTime, that.createdDateTime) &&
                Objects.equals(loggedUser, that.loggedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comments, guid, modifiedDateTime, clientGuid, createdDateTime, loggedUser);
    }
}
