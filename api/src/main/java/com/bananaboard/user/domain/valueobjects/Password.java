package com.bananaboard.user.domain.valueobjects;

import java.util.ArrayList;
import java.util.List;
import com.bananaboard.sharedkernel.validation.Result;
import com.bananaboard.sharedkernel.validation.Error;
import com.bananaboard.user.domain.errors.UserDomainError;

public class Password {
    private String value;
    public Password(String value){
        this.value = value;
    }
    public static Result<Password> create(String password){
        List<Error> errors = new ArrayList<Error>();
        
        if(!validLength(password)) errors.add(UserDomainError.PasswordError.TooShort);
        if(!atLeastOneLowerCase(password)) errors.add(UserDomainError.PasswordError.MissingLowercase);
        if(!atLeastOneNumber(password)) errors.add(UserDomainError.PasswordError.MissingNumber);
        if(!atLeastOneSpecialCharacter(password)) errors.add(UserDomainError.PasswordError.MissingSpecial);
        if(!atLeastOneUpperCase(password)) errors.add(UserDomainError.PasswordError.MissingUppercase);
        
        if(!errors.isEmpty()) return Result.failure(errors);
        
        return Result.success(new Password(password));
    }
     /**
     * <p>Checks if the password has following: </p>
     * <ul>
     *   <li> Minimum 8 characters in length. </li>
     *   <li> At least one uppercase English letter.</li>
     *   <li> At least one lowercase English letter.</li>
     *   <li> At least one digit. </li>
     *   <li> At least one special character. </li>
     * </ul>
     *
     * @return  If the password has the match returns true
     * 
     */
    private static boolean validLength(String password)  {
        return password.length() >= 8;
    }
    private static boolean atLeastOneUpperCase(String password){
        return password.matches(".*[A-Z].*");
    }
    private static boolean atLeastOneLowerCase(String password){
        return password.matches(".*[a-z].*");
    }
    private static boolean atLeastOneNumber(String password){
        return password.matches(".*[0-9].*");
    }
    private static boolean atLeastOneSpecialCharacter(String password){
        return password.matches(".*[#?!@$%^&*-].*");
    }
    public String getValue() {
        return value;
    }
}
