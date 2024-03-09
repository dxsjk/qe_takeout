package org.app.exception;

/**
 * 作者:疏狂难除
 * 时间:2024-02-08
 */
public class BaseException extends RuntimeException {
    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }
}
