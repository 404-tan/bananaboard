package com.bananaboard.user.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.shared.sharedkernel.validation.Error;
import com.bananaboard.shared.sharedkernel.valueobjects.Uuid;
import com.bananaboard.user.domain.valueobjects.IconName;
import com.bananaboard.user.domain.valueobjects.Image;

public class ProfileIcon {
    private Uuid id;
    private IconName name;
    private Image icon;
    
    private ProfileIcon(Image icon, IconName name){
        this.id = new Uuid();
        this.icon = icon;
        this.name = name;
    }
    public Result<ProfileIcon> create(String iconPath,String MIMEType,String iconName){
        List<Error> errors = new ArrayList<Error>();
        Result<IconName> resultIcon = IconName.create(iconName);
        Result<Image> resultImage = Image.create(iconPath, MIMEType);
        if(resultIcon.isFailure()) errors.addAll(resultIcon.getErrors());
        if(resultImage.isFailure()) errors.addAll(resultImage.getErrors());

        if(!errors.isEmpty()) return Result.failure(errors);

        Image icon = resultImage.getValue();
        IconName name = resultIcon.getValue();

        return Result.success(new ProfileIcon(icon,name));
    }
    public String getImagePath(){
        return icon.getPath();
    }
    public String getMIMEType(String MIMEType){
        return icon.getMimeType();
    }
    public UUID getId(){
        return id.getValue();
    }
    public String getIconName(){
        return name.getValue();
    }
}
