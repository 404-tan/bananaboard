package com.bananaboard.user.application.commands.handlers;

import com.bananaboard.shared.sharedkernel.validation.Result;

import java.util.Set;
import java.util.UUID;

import com.bananaboard.shared.sharedkernel.validation.Error;
import com.bananaboard.user.application.commands.CreateUserCommand;
import com.bananaboard.user.application.enums.RolesEnum;
import com.bananaboard.user.domain.builders.UserBuilder;
import com.bananaboard.user.domain.contracts.PasswordHasher;
import com.bananaboard.user.infra.repos.contracts.RoleRepository;
import com.bananaboard.user.infra.repos.contracts.UserRepository;

public class CreateUserCommandHandler {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordHasher passwordHasher;
    private final static String DEFAULT_ROLE = RolesEnum.getDefaultRole();
    public CreateUserCommandHandler(UserRepository userRepository,RoleRepository roleRepository,PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordHasher = passwordHasher;
    }
    public Result<Void> handle(CreateUserCommand command ){
        if(userRepository.existsByEmail(command.email())) 
            return Result.failure(new Error("CreateUserCommandHandler.Handle", "Email.AlreadyExists", "Email already exists."));
        
        if(userRepository.existsByUsername(command.username()))
            return Result.failure(new Error("CreateUserCommandHandler.Handle", "Username.AlreadyExists", "Username already exists."));
        
        var defaultRole = roleRepository.findByName(DEFAULT_ROLE);
        if (defaultRole.isEmpty()) 
            return Result.failure(new Error("CreateUserCommandHandler.Handle", "DefaultRole.NotFound", "Default role not found."));
        
        Set<UUID> roles = Set.of(defaultRole.get().getId());
        
        var userBuilder = new UserBuilder();
        
        var userResult = userBuilder
            .withUsername(command.username())
            .withEmail(command.email())
            .withPassword(command.password())
            .withBio(command.bio())
            .withProfileIcon(command.profileIconId())
            .withRoles(roles)
            .withPasswordHasher(passwordHasher)
            .build();
        
        userRepository.save(userResult.getValue());
        return Result.success(null);
    }
}
