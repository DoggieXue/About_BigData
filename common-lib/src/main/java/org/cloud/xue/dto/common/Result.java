package org.cloud.xue.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Result
 * @Description: 统一返回的数据结构
 * @Author: Doggie
 * @Date: 2023年08月09日 11:31:29
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements IResult {

    private long code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> failed(IResult iResult) {
        return new Result<T>(iResult.getCode(), iResult.getMsg(), null);
    }

    public static <T> Result<T> failed(IResult errResult, String msg) {
        return new Result<>(errResult.getCode(), msg, null);
    }

    public static <T> Result<T> failed(String msg) {
        return new Result<>(ResultEnum.FAILED.getCode(), msg, null);
    }

    public static <T> Result<T> failed() {
        return failed(ResultEnum.FAILED);
    }

    public static <T> Result<T> validateFailed() {
        return failed(ResultEnum.VALIDATE_FAILED);
    }

    public static <T> Result<T> unauthorized(T data) {
        return new Result<T>(ResultEnum.UNAUTHORIZED.getCode(),ResultEnum.UNAUTHORIZED.getMsg(), data);
    }

    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(ResultEnum.FORBIDDEN.getCode(),ResultEnum.FORBIDDEN.getMsg(), data);
    }
}
