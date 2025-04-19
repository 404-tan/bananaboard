package com.bananaboard.user.domain.errors;
import com.bananaboard.sharedkernel.validation.Error;
public final class UserDomainError {
    public static final class PasswordError {
        public static final Error TooShort = new Error("Password.TooShort", "The password must have at least 8 characters in length.");
        public static final Error MissingLowercase = new Error("Password.MissingLowercase", "The password must have at least one lower case letter.");
        public static final Error MissingUppercase = new Error("Password.MissingUppercase", "The password must have at least one upper case letter.");
        public static final Error MissingNumber = new Error("Password.MissingNumber", "The password must have at least one number.");
        public static final Error MissingSpecial = new Error("Password.MissingSpecial", "The password must have at least one special character.");
        
    }
    public static final class BiographyError {
        public static final Error Empty = new Error("Biography.Empty", "Biography cannot be empty.");
        public static final Error TooLong = new Error("Biography.TooLong", "Biography must have at most 1500 characters.");
    }
    public static final class EmailError {
        public static final Error Empty = new Error("Email.Empty", "Email must not be empty.");
        public static final Error Invalid = new Error("Email.InvalidFormat", "The email format is invalid.");
    }
    public static final class ImageError {
        public static final Error EmptyPath = new Error("Image.EmptyPath", "The image path cannot be empty.");
        public static final Error InvalidMIMEType = new Error("Image.InvalidMIMEType", "The MIME type is invalid.");
    }
    public static final class UsernameError {
        public static final Error Empty = new Error("Username.Empty", "The username cannot be empty.");
        public static final Error TooLong = new Error("Username.TooLong", "The username must have at most 30 characters.");
        public static final Error TooShort = new Error("Username.TooShort", "The username must have at least 3 characters.");
        public static final Error InvalidUsername = new Error("Username.InvalidUsername", "Username contains invalid characters.");
    }
    public static final class HashedPasswordError {
        public static final Error EmptyHash = new Error("HashedPassword.EmptyHash","The hash cannot be empty.");
    }
    public static final class UserError {
        public static final Error SamePasswordAsPrevious = new Error ("User.UpdatePassword.SamePasswordAsPrevious", "The password is the same as previous.");
    }
    public static final class UserBuilderError{
        public static final Error EmptyProfileIconId = new Error("UserBuilder.ProfileIconId.EmptyProfileIconId","The user profile icon must not be empty");
        public static final Error InvalidMinimumRoles = new Error("UserBuilder.Roles.InvalidMinimumRoles","The user must have at least one role");
        public static final Error MissingHasher = new Error ("UserBuilder.Hasher.Missing","Password hasher must be provided.");
    }
}
