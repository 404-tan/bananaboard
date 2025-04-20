package com.bananaboard.user.domain.errors;
import java.text.MessageFormat;

import com.bananaboard.shared.sharedkernel.validation.Error;
public final class UserDomainError {
    public static final class PasswordError {
        public static final Error TooShort = new Error("Password.Create","TooShort", "The password must have at least 8 characters in length.");
        public static final Error MissingLowercase = new Error("Password.Create","MissingLowercase", "The password must have at least one lower case letter.");
        public static final Error MissingUppercase = new Error("Password.Create","MissingUppercase", "The password must have at least one upper case letter.");
        public static final Error MissingNumber = new Error("Password.Create","MissingNumber", "The password must have at least one number.");
        public static final Error MissingSpecial = new Error("Password.Create","MissingSpecial", "The password must have at least one special character.");
        
    }
    public static final class BiographyError {
        public static final Error Empty = new Error("Biography.Create","Empty", "Biography cannot be empty.");
        public static final Error TooLong = new Error("Biography.Create","TooLong", "Biography must have at most 1500 characters.");
    }
    public static final class EmailError {
        public static final Error Empty = new Error("Email.Create","Empty", "Email must not be empty.");
        public static final Error Invalid = new Error("Email.Create","InvalidFormat", "The email format is invalid.");
    }
    public static final class ImageError {
        public static final Error EmptyPath = new Error("Image.Create","EmptyPath", "The image path cannot be empty.");
        public static final Error InvalidMIMEType = new Error("Image.Create","InvalidMIMEType", "The MIME type is invalid.");
    }
    public static final class UsernameError {
        public static final Error Empty = new Error("Username.Create","Empty", "The username cannot be empty.");
        public static final Error TooLong = new Error("Username.Create","TooLong", "The username must have at most 30 characters.");
        public static final Error TooShort = new Error("Username.Create","TooShort", "The username must have at least 3 characters.");
        public static final Error InvalidUsername = new Error("Username.Create","InvalidUsername", "Username contains invalid characters.");
    }
    public static final class HashedPasswordError {
        public static final Error EmptyHash = new Error("HashedPassword.Create","EmptyHash","The hash cannot be empty.");
        public static final Error HashingFailed = new Error("HashedPassword.Create", "HashingFailed", "The hashing of the password failed" );
    }
    public static final class UserError {
        public static final Error SamePasswordAsPrevious = new Error ("User.UpdatePassword","SamePasswordAsPrevious", "The password is the same as previous.");
    }
    public static final class UserBuilderError{
        public static final Error InvalidProfileIconId = new Error("UserBuilder.Build","ProfileIconId.InvalidProfileIconId","The user profile icon is invalid");
        public static final Error InvalidMinimumRoles = new Error("UserBuilder.Build","Roles.InvalidMinimumRoles","The user must have at least one role");
        public static final Error MissingHasher = new Error ("UserBuilder.Build","Hasher.Missing","Password hasher must be provided.");
        public static final Error InvalidRoleId(String id){
            return new Error("UserBuilder.Build","RolesIds.InvalidRoleId",MessageFormat.format("Invalid Role ID: ({0})",id));
        }
    }
}
