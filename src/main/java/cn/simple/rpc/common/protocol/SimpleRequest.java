package cn.simple.rpc.common.protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求信息
 * @author guochuang
 * @version v1
 * @date 2021/09/22 11:31:00
 */
public class SimpleRequest implements Serializable {
    private static final long serialVersionUID = -488571309811512199L;

    private String serviceName;

    private String method;

    private final Map<String, String> headers = new HashMap<>();

    private Class<?>[] parameterTypes;

    private Object[] parameters;

    public SimpleRequest() {
    }

    public SimpleRequest(String serviceName, String method, Class<?>[] parameterTypes, Object[] parameters) {
        this.serviceName = serviceName;
        this.method = method;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
