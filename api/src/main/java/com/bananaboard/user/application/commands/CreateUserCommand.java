package com.bananaboard.user.application.commands;

import java.util.UUID;

public record CreateUserCommand(
    String email,
    String username,
    String password,
    String bio,
    UUID profileIconId
) {

}
