package com.LeaveSystem.DTO.User;

import com.LeaveSystem.Domain.User.UserRole;

public record RegisterRequestDTO(String userName, String email, String password, UserRole role) {
}
