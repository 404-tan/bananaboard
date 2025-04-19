package com.bananaboard.user.domain.entities;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import com.bananaboard.sharedkernel.validation.Result;
import com.bananaboard.sharedkernel.valueobjects.Uuid;
import com.bananaboard.user.domain.errors.UserDomainError.UserError;
import com.bananaboard.user.domain.valueobjects.*;

public class User {
    private Uuid id;
    private Username username;
    private Email email;
    private HashedPassword password;
    private Uuid profileIconId;
    private Biography bio;
    private ActivityLog log;
    private Set<Uuid> roles;

    public User(Username username,
     Email email, 
     HashedPassword password, 
     Uuid profileIconId,
     Biography bio,
     Set<Uuid> roles){
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileIconId = profileIconId;
        this.bio = bio;
        this.roles = roles;
        this.id = new Uuid();
        this.log = new ActivityLog();
    }
    public String getId(){
        return this.id.toString();
    }
    public String getUsername() {
        return username.getValue();
    }
    public String getPassword(){
        return password.getValue();
    }
    public String getEmail(){
        return email.getEmail();
    }
    public String getProfileIconId(){
        return profileIconId.getValue();
    }
    public String getBio(){
        return bio.getText();
    }
    public String getLastSeen(){
        LocalDateTime now = LocalDateTime.now();
        
        long years = ChronoUnit.YEARS.between(log.getLastSeenAt(), now);
        if (years > 0) return years + "y";

        long months = ChronoUnit.MONTHS.between(log.getLastSeenAt(), now);
        if (months > 0) return months + "mo";

        long weeks = ChronoUnit.WEEKS.between(log.getLastSeenAt(), now);
        if (weeks > 0) return weeks + "w";

        long days = ChronoUnit.DAYS.between(log.getLastSeenAt(), now);
        if (days > 0) return days + "d";

        long hours = ChronoUnit.HOURS.between(log.getLastSeenAt(), now);
        if (hours > 0) return hours + "h";

        long minutes = ChronoUnit.MINUTES.between(log.getLastSeenAt(), now);
        if (minutes > 0) return minutes + "m";

        return "now";
    }
    public String getAccountAge(){
        Period periodBetweenCreatedAndNow = Period.between(log.getCreatedAt().toLocalDate(), LocalDate.now());
        if(periodBetweenCreatedAndNow.getYears() > 0) return periodBetweenCreatedAndNow.getYears() + " years";
        if(periodBetweenCreatedAndNow.getMonths() > 0) return periodBetweenCreatedAndNow.getMonths() + " months";
        if(periodBetweenCreatedAndNow.getDays() > 0) return periodBetweenCreatedAndNow.getMonths() + " days";
        return "now";
    }

    public List<String> getUserRoles(){
        return this.roles.stream()
                        .map(uuid -> uuid.getValue())
                        .toList();
    }
    public Result<Void> updatePassword(HashedPassword newPassword) {
        if (this.password.equals(newPassword)) return Result.failure(UserError.SamePasswordAsPrevious);
        this.password = newPassword;
        return Result.success();
    }

}
