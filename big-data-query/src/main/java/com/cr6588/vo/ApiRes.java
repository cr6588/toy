package com.cr6588.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create in 2022年04月03日
 * @category TODO
 * @author chenyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRes<T> {

    private static int SUCC_CODE = 0;
    private static int ERR_CODE = 100;

    private int status = 0;
    private T body;
    private Pager pager;
    private String msg;

    public static <T> ApiRes<T> succ(T body, Pager pager, String msg) {
        return new ApiRes<T>(SUCC_CODE, body, pager, msg);
    }

    public static <T> ApiRes<T> succ(T body, Pager pager) {
        return new ApiRes<T>(SUCC_CODE, body, pager, null);
    }

    public static <T> ApiRes<T> succ(T body, String msg) {
        return new ApiRes<T>(SUCC_CODE, body, null, msg);
    }

    public static ApiRes<String> emptySucc() {
        return new ApiRes<String>(SUCC_CODE, null, null, null);
    }

    public static <T> ApiRes<T> succ(T body) {
        return new ApiRes<T>(SUCC_CODE, body, null, null);
    }

    public static <T> ApiRes<T> err(T body, Pager pager, String msg) {
        return new ApiRes<T>(ERR_CODE, body, pager, msg);
    }

    public static <T> ApiRes<T> err(T body, String msg) {
        return new ApiRes<T>(ERR_CODE, body, null, msg);
    }

    public static <T> ApiRes<T> err(T body) {
        return new ApiRes<T>(ERR_CODE, body, null, null);
    }

    public static ApiRes<String> err(String msg) {
        return new ApiRes<String>(ERR_CODE, null, null, msg);
    }

    public static ApiRes<String> emptyErr() {
        return new ApiRes<String>(ERR_CODE, null, null, null);
    }
}
