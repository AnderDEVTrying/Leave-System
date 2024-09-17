package com.LeaveSystem.Domain.User;

public enum UserRole {
    ADMIN("admin"),
    REGULAR("regular");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
