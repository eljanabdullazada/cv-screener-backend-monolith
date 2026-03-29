package com.company.cvscreener.auth.controller;

import com.company.cvscreener.auth.dto.UpdateRoleRequest;
import com.company.cvscreener.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationService authenticationService;

    @PutMapping("/users/{username}/role")
    @PreAuthorize("hasRole('HR')")
    public void updateUserRole(@PathVariable String username, @RequestBody UpdateRoleRequest request) {
        authenticationService.updateUserRole(username, request.roleName());
    }
}
