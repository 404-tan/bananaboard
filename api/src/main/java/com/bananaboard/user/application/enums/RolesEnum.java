package com.bananaboard.user.application.enums;

public enum RolesEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MODERATOR("ROLE_MODERATOR"),
    SUPER_ADMIN("ROLE_SUPER_ADMIN");

    private final String roleName;

    RolesEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
    public static String getDefaultRole(){
        return USER.getRoleName();
    }
}
