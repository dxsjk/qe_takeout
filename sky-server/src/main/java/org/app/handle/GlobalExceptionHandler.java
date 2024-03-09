package org.app.handle;

import lombok.extern.slf4j.Slf4j;
import org.app.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.app.constant.Message.USERNAME_EXIST;

/**
 * 作者:疏狂难除
 * 时间:2024-03-09
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**[c]
     * 处理SQLIntegrityConstraintViolationException异常数据库异常
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        if(ex.getMessage().contains("Duplicate entry")){
            String[] messages = message.split(" ");
            String username=messages[2];
            String msg=username+USERNAME_EXIST;
            return Result.error(msg);
        }else {
            return Result.error(ex.getMessage());
        }
    }
}
