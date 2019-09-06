package org.jerfan.order.executor.base;

import java.io.Serializable;

/**
 * @author jerfan.cang
 * @date 2019/9/6  10:07
 */
public class ClazzObject implements Serializable {


    private static final long serialVersionUID = 4105274966373773332L;


    /**
     * 接口服务名称
     */
    private Class clazz;

    /**
     * 服务接口的方法名称
     */
    private String methodName;

    /**
     * 接口参数
     */
    private Object param;

    public ClazzObject(){

    }
    public ClazzObject(Class clazz,String methodName,Object param){
        this.clazz = clazz;
        this.methodName = methodName;
        this.param = param;
    }

    public Class getClazz() {
        return clazz;
    }


    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "ClazzObject{" +
                "clazz=" + clazz.getName() +
                ", methodName='" + methodName + '\'' +
                ", param=" + param +
                '}';
    }
}
