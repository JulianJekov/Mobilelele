package org.softuni.mobilelele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.softuni.mobilelele.model.enums.UserRoleEnum;

@Table(name = "roles")
@Entity
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public Role setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
