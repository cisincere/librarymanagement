package com.l.library.sysadmin.organization.exception;

import com.l.library.common.web.execption.DefaultGlobalExceptionHandlerAdvice;
import com.l.library.manangement.common.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public Result userNotFound(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType());
    }

    @ExceptionHandler(value = {RoleNotFoundException.class})
    public Result roleNotFound(RoleNotFoundException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType());
    }
}
