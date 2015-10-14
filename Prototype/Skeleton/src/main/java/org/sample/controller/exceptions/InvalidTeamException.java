package org.sample.controller.exceptions;

public class InvalidTeamException extends RuntimeException {

    public InvalidTeamException(String s) {
        super(s);
    }
}
