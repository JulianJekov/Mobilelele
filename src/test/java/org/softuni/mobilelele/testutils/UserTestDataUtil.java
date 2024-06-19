package org.softuni.mobilelele.testutils;

import org.softuni.mobilelele.model.entity.Role;
import org.softuni.mobilelele.model.entity.UserEntity;
import org.softuni.mobilelele.model.enums.UserRoleEnum;
import org.softuni.mobilelele.repository.RoleRepository;
import org.softuni.mobilelele.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    private void saveRoles() {
        List<Role> roles = List.of(new Role().setRole(UserRoleEnum.ADMIN), new Role().setRole(UserRoleEnum.USER));
        roleRepository.saveAll(roles);
    }

    private UserEntity createUserEntity(String email, List<UserRoleEnum> roles) {
        if (roleRepository.count() == 0) {
            saveRoles();
        }

        List<Role> allByRoleIn = this.roleRepository.findAllByRoleIn(roles);


        UserEntity user = new UserEntity()
                .setIsActive(true)
                .setEmail(email)
                .setFirstName("Test user first")
                .setLastName("Test user last")
                .setRoles(allByRoleIn);
        return this.userRepository.save(user);
    }

    public UserEntity createTestUser(String email) {
        return createUserEntity(email, List.of(UserRoleEnum.USER));
    }

    public UserEntity createTestAdmin(String email) {
        return createUserEntity(email, List.of(UserRoleEnum.ADMIN));
    }

    public void cleanUp() {
        this.userRepository.deleteAll();
        this.roleRepository.deleteAll();
    }
}
