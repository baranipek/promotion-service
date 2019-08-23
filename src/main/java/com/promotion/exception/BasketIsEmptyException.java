package com.promotion.exception;


public class BasketIsEmptyException extends RuntimeException {

    private static final long serialVersionUID = 2639035343220743209L;

    public BasketIsEmptyException(String message) {
        super(message);
    }
}
