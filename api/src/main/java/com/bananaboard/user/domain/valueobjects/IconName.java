package com.bananaboard.user.domain.valueobjects;

import java.util.ArrayList;
import java.util.List;

import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.user.domain.errors.UserDomainError;
import com.bananaboard.shared.sharedkernel.validation.Error;
public class IconName {
    /*
     * Allows underscores and alphanumerics excluding underscores at the start of the string
     * or at the end.
     */
    private static String ICONNAME_PATTERN = "^(?![_])[a-zA-Z0-9_]+(?<![_])$";
    private String normalizedValue;
    private IconName(String value){
        this.normalizedValue = value.toLowerCase().trim();
    }
    public static Result<IconName> create(String value){
        List<Error> errors = new ArrayList<Error>();
        if(value.trim().isEmpty()) errors.add(UserDomainError.IconNameError.Empty);
        if(!validPattern(value)) errors.add(UserDomainError.IconNameError.Invalid);
        if(!validMinimumLength(value) ) errors.add(UserDomainError.IconNameError.TooShort);
        if(!validMaximumLength(value)) errors.add(UserDomainError.IconNameError.TooLong);
        if(!errors.isEmpty()) return Result.failure(errors);
        return Result.success(new IconName(value));
    }
    private static boolean validMinimumLength (String iconName){
        return iconName.length() >= 3;
    }
    private static boolean validMaximumLength(String iconName){
        return iconName.length() <= 30;
    }
    private static boolean validPattern(String iconName){
        return iconName.matches(ICONNAME_PATTERN);
    }
    public String getValue(){
        return normalizedValue;
    }
}
