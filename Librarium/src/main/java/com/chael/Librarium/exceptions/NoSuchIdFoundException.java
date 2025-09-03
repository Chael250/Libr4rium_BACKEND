package com.chael.Librarium.exceptions;

import java.util.UUID;

public class NoSuchIdFoundException extends RuntimeException {
    public NoSuchIdFoundException(UUID id) {
        super("No such id found : " + id);
    }
}
