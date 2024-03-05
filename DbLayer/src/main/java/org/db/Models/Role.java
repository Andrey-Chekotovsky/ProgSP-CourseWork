package org.db.Models;

public enum Role {
    ADMIN,
    CUSTOMER,
    COMPANY_AGENT;

    public static Role fromString(String role) {
        return switch (role) {
            case "ROLE_ADMIN" -> ADMIN;
            case "ROLE_CUSTOMER" -> CUSTOMER;
            case "ROLE_COMPANY_AGENT" -> COMPANY_AGENT;
            default -> CUSTOMER;
        };
    }
}
