package org.pancakeapple.exception;

public class DuplicateFavoriteException extends BaseException{
    public DuplicateFavoriteException() {
    }
    public DuplicateFavoriteException(String msg) {
        super(msg);
    }
}
