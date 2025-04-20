package com.bananaboard.user.domain.builders;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.bananaboard.shared.sharedkernel.builders.BaseBuilder;
import com.bananaboard.shared.sharedkernel.exceptions.InvalidUuidException;
import com.bananaboard.shared.sharedkernel.utils.UuidUtils;
import com.bananaboard.shared.sharedkernel.validation.Error;
import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.shared.sharedkernel.valueobjects.Uuid;
import com.bananaboard.user.domain.contracts.PasswordHasher;
import com.bananaboard.user.domain.entities.User;
import com.bananaboard.user.domain.errors.UserDomainError;
import com.bananaboard.user.domain.valueobjects.*;



public final class UserBuilder extends BaseBuilder<User> {
    private Username username;
    private Email email;
    private Password password;
    private Biography bio;
    private Uuid profileIconId;
    private Set<Uuid> rolesIds = new HashSet<>();
    private PasswordHasher hasher;

    private final List<Error> errors = new ArrayList<>();

    public UserBuilder withUsername(String username) {
        applyIfSuccess(Username.create(username),  u -> this.username = u);
        return this;
    }

    public UserBuilder withEmail(String email) {
        applyIfSuccess(Email.create(email), e -> this.email = e);
        return this;
    }

    public UserBuilder withPassword(String password) {
        applyIfSuccess(Password.create(password), p -> this.password = p);
        return this;
    }

    public UserBuilder withBio(String bio) {
        applyIfSuccess(Biography.create(bio),b -> this.bio = b);
        return this;
    }

    public UserBuilder withProfileIcon(String profileIconId) {
        applyIfSuccess(parseProfileIconUuid(profileIconId), p -> this.profileIconId = p);
        return this;
    }

    public UserBuilder withRoles(Set<String> rolesIds) {
        if (rolesIds.isEmpty()) errors.add(UserDomainError.UserBuilderError.InvalidMinimumRoles);
        applyIfSuccess(parseRolesUuid(rolesIds),rIds -> this.rolesIds = rIds);
        return this;
    }

    public UserBuilder withPasswordHasher(PasswordHasher hasher) {
        if (hasher == null) errors.add(UserDomainError.UserBuilderError.MissingHasher);
        this.hasher = hasher;
        return this;
    }

    public Result<User> build() {
        Result<HashedPassword> hashedPasswordResult = HashedPassword.create(this.password, this.hasher);
        if(hashedPasswordResult.isFailure()) this.errors.addAll(hashedPasswordResult.getErrors());
        if (!errors.isEmpty()) return Result.failure(errors);
        


        User user = new User(
            username,
            email,
            hashedPasswordResult.getValue(),
            profileIconId,
            bio,
            rolesIds
        );

        return Result.success(user);
    }
    private Result<Uuid> parseProfileIconUuid(String profileIconId){
        try{
            return Result.success(new Uuid(profileIconId));
        }catch(InvalidUuidException e){
            return Result.failure(UserDomainError.UserBuilderError.InvalidProfileIconId);
        }
    }
    private Result<Set<Uuid>> parseRolesUuid(Set<String> rolesIds){
        try{
            return Result.success(UuidUtils.parseUuidSet(rolesIds));
        }catch(InvalidUuidException e){
            return Result.failure(UserDomainError.UserBuilderError.InvalidRoleId(e.getInvalidId()));
        }
    }

}
