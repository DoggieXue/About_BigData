package org.cloud.xue.dto.common;

/**
 * 常用结果枚举
 */
public enum ResultEnum implements IResult {
    SUCCESS(0, "交易成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已过期"),
    FORBIDDEN(403, "没有权限访问资源")

    ;

    private long code;
    private String msg;

    ResultEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }
    @Override
    public String getMsg() {
        return msg;
    }
}
