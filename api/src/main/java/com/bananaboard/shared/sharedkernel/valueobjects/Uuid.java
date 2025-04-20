package com.bananaboard.shared.sharedkernel.valueobjects;

import java.util.UUID;

import com.bananaboard.shared.sharedkernel.exceptions.InvalidUuidException;

public class Uuid {
    private UUID value;
    public Uuid () {
        this.value = UUID.randomUUID();
    }
    public Uuid(String uuid) throws InvalidUuidException 
    {
        try{
            this.value = UUID.fromString(uuid);
        }catch(IllegalArgumentException ex){
            throw new InvalidUuidException(uuid);
        }
    }
    public UUID getValue() {
        return this.value;
    }
}
