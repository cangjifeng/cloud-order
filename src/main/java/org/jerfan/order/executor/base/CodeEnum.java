package org.jerfan.order.executor.base;

/**
 * 错误码枚举
 */
public enum CodeEnum {

    PARAM_EMPTY("-1","入参为空"),
    EXECUTE_SERVER_FAIL("0","服务执行失败"),
    EXECUTE_SERVER_SUCCESS("1","服务执行成功"),
    EXECUTE_SERVER_EXCEPTION("2","服务执行发生异常");

    private String code;

    private String message;

    CodeEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
