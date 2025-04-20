package com.bananaboard.user.domain.valueobjects;

import java.util.ArrayList;
import java.util.List;

import com.bananaboard.shared.sharedkernel.validation.Error;
import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.user.domain.errors.UserDomainError;

public class Email {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private String value;

    private Email(String email){
        this.value = email.toLowerCase().trim();
    }

    public static Result<Email> create(String email){
        List<Error> errors = new ArrayList<Error>();
        if (email.trim().isEmpty()) errors.add(UserDomainError.EmailError.Empty);
        if(!validEmailPattern(email)) errors.add(UserDomainError.EmailError.Invalid);
        if(!errors.isEmpty()) return Result.failure(errors);
        return Result.success(new Email(email));
    }

    public String getEmail(){
        return this.value;
    }
    private static boolean validEmailPattern(String email){
        return email.matches(EMAIL_PATTERN);
    }
}
