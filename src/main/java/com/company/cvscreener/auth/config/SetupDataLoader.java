package com.company.cvscreener.auth.config;

import com.company.cvscreener.auth.domain.Role;
import com.company.cvscreener.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) {
        createRoleIfNotFound("CANDIDATE");
        createRoleIfNotFound("HR");
    }

    private void createRoleIfNotFound(String name) {
        roleRepository.findByName(name).ifPresentOrElse(
                role -> System.out.println("Role " + name + " already exists. Skipping."),
                () -> {
                    Role role = new Role();
                    role.setName(name);
                    roleRepository.save(role);
                    System.out.println("Created Role: " + name);
                }
        );
    }

//    private void createRoleIfNotFound(String name) {
//        roleRepository.findByName(name).ifPresentOrElse(
//                role -> System.out.println("Role " + name + " already exists."),
//                () -> {
//                    Role role = new Role();
//                    // Assign your specific UUID for the HR
//                    if ("HR".equals(name)) {
//                        role.setId(UUID.fromString("ff4a65e1-d4cf-4afe-be0c-8d73c452a565"));
//                    }
//                    role.setName(name);
//
//                    // Use saveAndFlush to ensure it hits the DB immediately
//                    // and clears any "detached entity" confusion
//                    roleRepository.save(role);
//                    System.out.println("Created Role: " + name + " with ID: " + role.getId());
//                }
//        );
//    }
}