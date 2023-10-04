package org.pancakeapple.exception;

public class UserNameExistException extends BaseException{
    public UserNameExistException() {
    }
    public UserNameExistException(String msg) {
        super(msg);
    }
}
