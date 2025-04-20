package com.bananaboard.user.domain.valueobjects;

import java.util.ArrayList;
import java.util.List;

import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.shared.sharedkernel.validation.Error;
import com.bananaboard.user.domain.errors.UserDomainError;

public class RoleName {
    /*
     * Allows underscores and alphabetics excluding underscores at the start of the string
     * or at the end.
     */
    private static String ROLENAME_PATTERN = "^(?![_])[a-zA-Z_]+(?<![_])$";
    private String normalizedValue;
    private RoleName(String value){
        this.normalizedValue = value.toUpperCase().trim();
    }
    public static Result<RoleName> create(String value){
        List<Error> errors = new ArrayList<Error>();
        if(value.trim().isEmpty()) errors.add(UserDomainError.RoleNameError.Empty);
        if(!validPattern(value)) errors.add(UserDomainError.RoleNameError.Invalid);
        if(!validMinimumLength(value) ) errors.add(UserDomainError.RoleNameError.TooShort);
        if(!validMaximumLength(value)) errors.add(UserDomainError.RoleNameError.TooLong);
        if(!errors.isEmpty()) return Result.failure(errors);
        return Result.success(new RoleName(value));
    }
    private static boolean validMinimumLength (String roleName){
        return roleName.length() >= 3;
    }
    private static boolean validMaximumLength(String roleName){
        return roleName.length() <= 30;
    }
    private static boolean validPattern(String roleName){
        return roleName.matches(ROLENAME_PATTERN);
    }
    public String getValue(){
        return normalizedValue;
    }
}
