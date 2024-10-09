package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scm.entities.Role;
import com.scm.repositories.RoleRepository;
import com.scm.helper.AppConstant;

import java.util.Optional;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing roles...");

        createRoleIfNotFound(AppConstant.ROLE_USER);
        createRoleIfNotFound(AppConstant.ROLE_PATIENT);
        createRoleIfNotFound(AppConstant.ROLE_DOCTOR);
        createRoleIfNotFound(AppConstant.ROLE_ADMIN);

        System.out.println("Roles initialization completed.");
    }

    private void createRoleIfNotFound(String roleName) {
        Optional<Role> roleOpt = roleRepository.findByRoleName(roleName);
        if (roleOpt.isEmpty()) {
            Role role = new Role();
            role.setRoleName(roleName);
            roleRepository.save(role);
            System.out.println("Created role: " + roleName);
        }
    }
}
