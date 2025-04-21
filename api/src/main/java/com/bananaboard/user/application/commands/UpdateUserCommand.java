package com.bananaboard.user.application.commands;

import java.util.Optional;
import java.util.UUID;

public record UpdateUserCommand(
    UUID userId,
    Optional<String> username,
    Optional<String> bio,
    Optional<UUID> profileIconId
) {}