package org.softuni.mobilelele.repository;

import org.softuni.mobilelele.model.entity.Role;
import org.softuni.mobilelele.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(UserRoleEnum userRole);
    List<Role> findAllByRoleIn(List<UserRoleEnum> roles);
}
