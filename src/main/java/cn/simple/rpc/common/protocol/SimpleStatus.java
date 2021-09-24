package cn.simple.rpc.common.protocol;

/**
 * 响应状态码
 * @author guochuang
 * @version v1
 * @date 2021/09/22 11:38:00
 */
public enum SimpleStatus {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR"),
    NOT_FOUND(404, "NOT FOUND");

    private int code;

    private String message;

    SimpleStatus() {
    }

    SimpleStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
