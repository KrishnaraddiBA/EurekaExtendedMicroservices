package com.userService.exception;

import lombok.Data;

@Data
public class ResurceNotFoundException extends RuntimeException{

    private String message;


    public ResurceNotFoundException(String message) {
        String.format("%s not match for above details",message);
        this.message = message;
    }
    public ResurceNotFoundException(){
        super("Resource not Found For Partcular User");
    }
}
