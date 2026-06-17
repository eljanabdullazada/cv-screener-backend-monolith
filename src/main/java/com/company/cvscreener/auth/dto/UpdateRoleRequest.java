package com.company.cvscreener.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoleRequest(

      @NotBlank(message = "Role name is required.")
      String roleName

) {}