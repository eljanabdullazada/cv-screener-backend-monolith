package com.company.cvscreener.auth.service;

import com.company.cvscreener.auth.domain.Role;
import com.company.cvscreener.auth.domain.User;
import com.company.cvscreener.auth.repository.RoleRepository;
import com.company.cvscreener.auth.repository.UserRepository;
import com.company.cvscreener.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // ==========================
    // LOGIN
    // ==========================

    public String authenticate(String username, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        var user = userRepository.findByUsername(username)
                .orElseThrow();

        return jwtService.generateToken(user.getUsername());
    }

    // ==========================
    // REGISTER CANDIDATE
    // ==========================

    public String registerCandidate(String username, String email, String password) {

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }

        Role candidateRole = roleRepository
                .findByName("CANDIDATE")
                .orElseThrow(() -> new RuntimeException("Error: Role 'CANDIDATE' not found in database. Check your 'roles' table!"));

        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
//                .enabled(true)
                .roles(Set.of(candidateRole))
                .build();

        userRepository.save(user);

        return jwtService.generateToken(user.getUsername());
    }

    // ==========================
    // REGISTER HR
    // ==========================

    public String registerHr(String username, String email, String password){
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("User already exists.");
        }

        Role hrRole = roleRepository
                .findByName("HR")
                .orElseThrow(() -> new RuntimeException("Error: Role 'HR' not found in database. Check your 'roles' table!"));

        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .enabled(true)
                .roles(Set.of(hrRole))
                .build();

        userRepository.save(user);

        return jwtService.generateToken((user.getUsername()));
    }
}