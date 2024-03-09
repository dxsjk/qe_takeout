package org.app.exception;

/**
 * 作者:疏狂难除
 * 时间:2024-02-08
 */
public class AccountNotFoundException extends BaseException{
    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }

}
