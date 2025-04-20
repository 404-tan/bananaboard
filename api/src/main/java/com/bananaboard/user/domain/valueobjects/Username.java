package com.bananaboard.user.domain.valueobjects;

import java.util.ArrayList;
import java.util.List;

import com.bananaboard.shared.sharedkernel.validation.Error;
import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.user.domain.errors.UserDomainError;

public class Username {
    /*
     * Allows underscores,alphanumerics and dot (.), excluding dots or underscores at the start of the string
     * or at the end.
     */
    private static String USERNAME_PATTERN = "^(?![_.])[a-zA-Z0-9._]+(?<![_.])$";
    private String normalizedValue;
    private Username(String value){
        this.normalizedValue = value.toLowerCase().trim();
    }
    public static Result<Username> create(String value){
        List<Error> errors = new ArrayList<Error>();
        if(value.trim().isEmpty()) errors.add(UserDomainError.UsernameError.Empty);
        if(!validPattern(value)) errors.add(UserDomainError.UsernameError.Invalid);
        if(!validMinimumLength(value) ) errors.add(UserDomainError.UsernameError.TooShort);
        if(!validMaximumLength(value)) errors.add(UserDomainError.UsernameError.TooLong);
        if(!errors.isEmpty()) return Result.failure(errors);
        return Result.success(new Username(value));
    }
    private static boolean validMinimumLength (String username){
        return username.length() >= 3;
    }
    private static boolean validMaximumLength(String username){
        return username.length() <= 30;
    }
    private static boolean validPattern(String username){
        return username.matches(USERNAME_PATTERN);
    }
    public String getValue(){
        return normalizedValue;
    }
}
