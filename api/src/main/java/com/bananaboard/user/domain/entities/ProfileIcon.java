package com.bananaboard.user.domain.entities;

import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.shared.sharedkernel.valueobjects.Uuid;
import com.bananaboard.user.domain.valueobjects.Image;

public class ProfileIcon {
    private Uuid id;
    private Image icon;
    
    private ProfileIcon(Image icon){
        this.id = new Uuid();
        this.icon = icon;
    }
    public Result<ProfileIcon> create(String iconPath,String MIMEType){
        Result<Image> result = Image.create(iconPath, MIMEType);
        if(result.isFailure()) return Result.failure(result.getErrors());
        Image icon = result.getValue();
        return Result.success(new ProfileIcon(icon));
    }
    public String getImagePath(){
        return icon.getPath();
    }
    public String getMIMEType(String MIMEType){
        return icon.getMimeType();
    }
    public String getId(){
        return id.getValue();
    }
}
