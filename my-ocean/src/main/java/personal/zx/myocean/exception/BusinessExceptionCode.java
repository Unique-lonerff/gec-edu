package personal.zx.myocean.exception;

/**
 * @description: 业务异常码
 * @author: Unique_zx
 * @date: 2021/12/26  16:16
 * @version: 1.0
 */

public enum BusinessExceptionCode {
    USER_LOGIN_NAME_EXIST(10001,"登录名已存在"),
    LOGIN_USER_ERROR(10002,"用户名不存在或密码错误"),
    VOTE_REPEAT(10003,"您已点赞过") ;

    private final int code;
    private final String message;

    BusinessExceptionCode(int code, String message) {
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
