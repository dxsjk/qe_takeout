package org.app.exception;

/**
 * 作者:疏狂难除
 * 时间:2024-02-18
 */
public class AccountLockException extends BaseException{
    public AccountLockException() {
    }

    public AccountLockException(String msg) {
        super(msg);
    }
}
