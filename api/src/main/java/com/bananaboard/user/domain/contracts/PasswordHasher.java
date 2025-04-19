package com.bananaboard.user.domain.contracts;

import com.bananaboard.user.domain.valueobjects.HashedPassword;
import com.bananaboard.user.domain.valueobjects.Password;

public interface PasswordHasher {
    HashedPassword hash(Password password);
    boolean verify(String rawPassword, HashedPassword hashedPassword);
}