package org.cleverdevtest.test.user.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность пользователя выводимого в новой системе
 */
@Entity
@Table(name = "company_user")
public class CompanyUserEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", nullable = false, updatable = false)
    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!getClass().isInstance(o)) {
            return false;
        }
        CompanyUserEntity other = (CompanyUserEntity) o;
        return this.id != null && Objects.equals(this.id, other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

}
