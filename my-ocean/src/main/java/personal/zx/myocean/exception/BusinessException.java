package personal.zx.myocean.exception;

/**
 * @author 小z
 * @description: 业务异常
 * @date 2024年10月21日 上午10:21
 */

public class BusinessException extends RuntimeException {
    //业务异常消息
    private BusinessExceptionCode code;

    public BusinessException(BusinessExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public BusinessExceptionCode getCode() {
        return code;
    }

    public void setCode(BusinessExceptionCode code) {
        this.code = code;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
