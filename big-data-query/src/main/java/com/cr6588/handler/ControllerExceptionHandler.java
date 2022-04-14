package com.cr6588.handler;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr6588.exception.ServiceException;
import com.cr6588.vo.ApiRes;

import lombok.extern.slf4j.Slf4j;

/**
 * create in 2022年04月07日
 * @category TODO
 * @author chenyi
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ApiRes<String> handleServiceException(ServiceException e) {
        log.error(e.getMessage(), e);
        return ApiRes.err(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ApiRes<String> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ApiRes.err(message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiRes<String> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ApiRes.err(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ApiRes<String> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ApiRes.err(e.getMessage());
    }
}
