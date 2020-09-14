package com.gordon.Dhelve.exception;

public class Dhelve extends RuntimeException {

    public Dhelve(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public Dhelve(String exMessage) {
        super(exMessage);
    }
}
