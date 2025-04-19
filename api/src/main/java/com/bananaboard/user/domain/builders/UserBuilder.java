package com.bananaboard.user.domain.builders;

import com.bananaboard.sharedkernel.validation.Result;
import com.bananaboard.sharedkernel.valueobjects.Uuid;
import com.bananaboard.sharedkernel.utils.UuidUtils;
import com.bananaboard.sharedkernel.validation.Error;
import com.bananaboard.user.domain.contracts.PasswordHasher;
import com.bananaboard.user.domain.entities.User;
import com.bananaboard.user.domain.errors.UserDomainError;
import com.bananaboard.user.domain.valueobjects.*;
import java.util.*;

public final class UserBuilder {
    private static final String EMPTY = "";
    
    private String password = EMPTY;
    private String email = EMPTY;
    private String username = EMPTY;
    private String profileIconId = EMPTY;
    private String bio = EMPTY;
    private Set<String> rolesIds = new HashSet<>();
    private PasswordHasher hasher;

    public UserBuilder withUsername(String username){
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public UserBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder withRoles(Set<String> rolesIds){
        this.rolesIds = rolesIds;
        return this;
    }

    public UserBuilder withBio(String bio){
        this.bio = bio;
        return this;
    }

    public UserBuilder withProfileIcon(String profileIconId){
        this.profileIconId = profileIconId;
        return this;
    }

    public UserBuilder withPasswordHasher(PasswordHasher hasher) {
        this.hasher = hasher;
        return this;
    }

    public Result<User> build() {
        List<Error> errors = new ArrayList<>();

        Result<Username> usernameResult = Username.create(username);
        if (usernameResult.isFailure()) errors.addAll(usernameResult.getErrors());

        Result<Email> emailResult = Email.create(email);
        if (emailResult.isFailure()) errors.addAll(emailResult.getErrors());

        Result<Password> passwordResult = Password.create(password);
        if (passwordResult.isFailure()) errors.addAll(passwordResult.getErrors());

        Result<Biography> bioResult = Biography.create(bio);
        if (bioResult.isFailure()) errors.addAll(bioResult.getErrors());

        if (hasher == null) {
            errors.add(UserDomainError.UserBuilderError.MissingHasher);
        }
        if ( profileIconId == null || profileIconId.isEmpty()) errors.add(UserDomainError.UserBuilderError.EmptyProfileIconId);
        
        if(rolesIds.size() < 1) errors.add(UserDomainError.UserBuilderError.InvalidMinimumRoles);
        Result<Set<Uuid>> resultRolesUuid = UuidUtils.parseUuidSet(rolesIds, "UserBuilder.Roles");
        

        if(resultRolesUuid.isFailure()) errors.addAll(resultRolesUuid.getErrors());
        if (!errors.isEmpty()) return Result.failure(errors);

        HashedPassword hashedPassword = hasher.hash(passwordResult.getValue());

        User user = new User(
            usernameResult.getValue(),
            emailResult.getValue(),
            hashedPassword,
            new Uuid(profileIconId),
            bioResult.getValue(),
            resultRolesUuid.getValue()
        );

        return Result.success(user);
    }
}
