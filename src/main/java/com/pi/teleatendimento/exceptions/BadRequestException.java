package com.pi.teleatendimento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -9221648404855307733L;

    /**
     * Constructs a <code>NotFoundException</code> with the specified message.
     *
     * @param message
     *            the detail message.
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code NotFoundException} with the specified message and root
     * cause.
     *
     * @param message
     *            the detail message.
     * @param t
     *            root cause
     */
    public BadRequestException(String message, Throwable t) {
        super(message, t);
    }
}