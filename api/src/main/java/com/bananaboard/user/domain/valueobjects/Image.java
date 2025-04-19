package com.bananaboard.user.domain.valueobjects;

import com.bananaboard.sharedkernel.validation.Result;
import com.bananaboard.user.domain.errors.UserDomainError;

import java.util.ArrayList;
import java.util.List;

import com.bananaboard.sharedkernel.validation.Error;
public class Image {
    private final String path;
    private final String mimeType;

    private Image(String path, String mimeType) {
        this.path = path;
        this.mimeType = mimeType;
    }

    public static Result<Image> create(String path, String mimeType) {
        List<Error> errors = new ArrayList<Error>();
        if (path == null || path.trim().isEmpty()) errors.add(UserDomainError.ImageError.EmptyPath);
        if (mimeType == null || !isValidMimeType(mimeType)) errors.add(UserDomainError.ImageError.InvalidMIMEType);
        if(!errors.isEmpty()) return Result.failure(errors);
        return Result.success(new Image(path.trim(), mimeType.toLowerCase()));
    }

    private static boolean isValidMimeType(String mime) {
        List<String> allowedTypes = List.of("image/png", "image/jpeg", "image/jpg", "image/gif", "image/webp");
        return allowedTypes.contains(mime.toLowerCase());
    }

    public String getPath() {
        return path;
    }

    public String getMimeType() {
        return mimeType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Image)) return false;
        Image other = (Image) obj;
        return path.equals(other.path) && mimeType.equals(other.mimeType);
    }

    @Override
    public int hashCode() {
        return path.hashCode() ^ mimeType.hashCode();
    }

    @Override
    public String toString() {
        return "Image(" + path + ", " + mimeType + ")";
    }
}
