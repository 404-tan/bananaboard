package com.bananaboard.user.domain.valueobjects;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import com.bananaboard.sharedkernel.validation.Result;
import com.bananaboard.sharedkernel.validation.Error;
import com.bananaboard.user.domain.errors.UserDomainError;

public class Biography {
    private String htmlText;
    public Biography(String htmlText){
        this.htmlText = htmlText;
    }
    public static Result<Biography> create(String htmlText){
        List<Error> errors = new ArrayList<Error>();
        if(htmlText.trim().isEmpty()) errors.add(UserDomainError.BiographyError.Empty);
        String cleanedHtmlText = Jsoup.clean(htmlText, Safelist.basic());
        if(!validMaxCharacters(cleanedHtmlText)) errors.add(UserDomainError.BiographyError.TooLong);
        if(!errors.isEmpty()) return Result.failure(errors);
        return Result.success(new Biography(cleanedHtmlText ));
    }
    public String getText(){
        return this.htmlText;
    }
    private static boolean validMaxCharacters(String htmlText){
        return htmlText.length() <= 1500;
    }
}
