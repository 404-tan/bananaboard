package com.bananaboard.user.application.commands.handlers;
import com.bananaboard.shared.sharedkernel.validation.Result;

import java.util.ArrayList;

import com.bananaboard.shared.sharedkernel.validation.Error;
import com.bananaboard.shared.sharedkernel.valueobjects.Uuid;
import com.bananaboard.user.application.commands.UpdateUserCommand;
import com.bananaboard.user.domain.valueobjects.Biography;
import com.bananaboard.user.domain.valueobjects.Username;
import com.bananaboard.user.infra.repos.contracts.UserRepository;

public class UpdateUserCommandHandler {
    
    private final UserRepository userRepository;

    public UpdateUserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Result<Void> handle(UpdateUserCommand cmd) {
        var userResult = userRepository.findById(cmd.userId());
        if (userResult == null) return Result.failure(new Error("UpdateUserCommandHandler.Handle", "User.NotFound", "User not found."));
        
        var user = userResult.get();
        var errors = new ArrayList<Error>();
    

        cmd.username().ifPresent(newUsername -> {
            if(userRepository.existsByUsername(newUsername)){
                errors.add(new Error("UpdateUserCommandHandler.Handle", "Username.AlreadyExists", "Username already exists."));
                return;
            } 
                
            var usernameDomainResult = Username.create(newUsername);
            if(usernameDomainResult.isFailure()){
                errors.addAll(usernameDomainResult.getErrors());
                return;
            } 

            var updateUsernameResult = user.updateUsername(usernameDomainResult.getValue());
            if (updateUsernameResult.isFailure()) errors.addAll(updateUsernameResult.getErrors());
        });

        cmd.bio().ifPresent(newBio -> {
            var biographyDomainResult = Biography.create(newBio);
            if(biographyDomainResult.isFailure()){
                errors.addAll(biographyDomainResult.getErrors());
                return;
            } 

            var updateBiographyResult = user.updateBio(biographyDomainResult.getValue());
            if (updateBiographyResult.isFailure()) errors.addAll(updateBiographyResult.getErrors());
        });

        cmd.profileIconId().ifPresent(newIconId -> {
            var profileIconId = new Uuid(newIconId);

            var updateProfileIconIdResult = user.updateProfileIconId(profileIconId);
            if (updateProfileIconIdResult.isFailure()) errors.addAll(updateProfileIconIdResult.getErrors());
        });

        if (!errors.isEmpty()) return Result.failure(errors);
        
        userRepository.save(user);
        return Result.success();
    }
}
