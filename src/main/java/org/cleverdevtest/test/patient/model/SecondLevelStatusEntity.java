package org.cleverdevtest.test.patient.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "second_level_status")
public class SecondLevelStatusEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "name", nullable = false, updatable = false)
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String secondLevelStatus) {
        this.name = secondLevelStatus;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!getClass().isInstance(o)) {
            return false;
        }
        SecondLevelStatusEntity other = (SecondLevelStatusEntity) o;
        return this.id != null && Objects.equals(this.id, other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
