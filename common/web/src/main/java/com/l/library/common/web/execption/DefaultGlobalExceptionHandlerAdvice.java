package com.l.library.common.web.execption;

import com.l.library.manangement.common.core.entity.vo.Result;
import com.l.library.manangement.common.core.exception.BaseException;
import com.l.library.manangement.common.core.exception.SystemErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.util.Objects;

@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Result missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("missing servlet request parameter exception:{}", e.getMessage());
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Result multipartException(MultipartException e) {
        log.error("upload file size limit:{}", e.getMessage());
        return Result.fail(SystemErrorType.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result serviceException(MethodArgumentNotValidException e) {
        log.error("service exception:{}", e.getMessage());
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Result duplicateKeyException(DuplicateKeyException e) {
        log.error("primary key exception:{}", e.getMessage());
        return Result.fail(SystemErrorType.DUPLICATE_PRIMARY_KEY);
    }

    @ExceptionHandler(value = {BaseException.class})
    public Result baseException(BaseException e) {
        log.error("base exception: {}", e.getMessage());
        return Result.fail(e.getErrorType());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception() {
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable() {
        return Result.fail();
    }
}
