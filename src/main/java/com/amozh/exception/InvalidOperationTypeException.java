package com.amozh.exception;

/**
 * Created by Andrii Mozharovskyi on 22.04.2016.
 */
public class InvalidOperationTypeException extends RuntimeException {
    public InvalidOperationTypeException(String message) {
        super(message);
    }
}
