package org.jerfan.order.queue.base;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author jerfan.cang
 * @date 2019/9/6  10:14
 */
public class ResultBean<T>  implements Serializable {

    private static final long serialVersionUID = 1175292287758109694L;

    /**
     * 错误码 {-1 请求入参为空，1 接口服务执行成功，other code 对应错误码列表}
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 异常信息
     */
    private Exception exception;

    /**
     * 异常堆栈信息
     */
    private Throwable throwable;

    /**
     * 数据 默认是 null
     */
    private T data;

    public ResultBean(){}

    public ResultBean(String code,String message){
        this.code = code;
        this.message = message;
    }
    public ResultBean(String code,String message,T data){
        this.code= code;
        this.message = message;
        this.data = data;
    }
    public ResultBean(String code,String message,Exception exception){
        this.code = code;
        this.message = message;
        this.exception = exception;
    }

    public ResultBean(String code,String message,Throwable throwable){
        this.code = code;
        this.message = message;
        this.throwable = throwable;
    }

    /**
     * 检查this 是否有错误或者异常
     * @return true 没有错误 false 有错误
     */
    public boolean hasError(){
        if(null != exception || null != throwable){
            return  true;
        }
        return false;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", exception=" + exception +
                ", throwable=" + throwable +
                ", data=" + data +
                '}';
    }
}
