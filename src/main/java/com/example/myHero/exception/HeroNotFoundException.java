package com.example.myHero.exception;
public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException(String message) {
        super(message);
    }
}
