package com.cr6588.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * create in 2022年04月07日
 * @category TODO
 * @author chenyi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 8328810299080183100L;
    private int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

}
