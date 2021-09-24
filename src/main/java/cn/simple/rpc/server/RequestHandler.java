package cn.simple.rpc.server;

import cn.simple.rpc.common.protocol.SimpleRequest;
import cn.simple.rpc.common.protocol.SimpleResponse;
import cn.simple.rpc.common.protocol.SimpleStatus;
import cn.simple.rpc.common.protocol.MessageProtocol;
import cn.simple.rpc.server.register.ServiceObject;
import cn.simple.rpc.server.register.ServiceRegister;

import java.lang.reflect.Method;

/**
 * 请求处理者，提供解组请求、编组响应等操作
 *
 * @author guochuang
 * @version v1
 * @date 2021/09/22 13:42:00
 */
public class RequestHandler {

    private MessageProtocol protocol;

    private ServiceRegister serviceRegister;

    public RequestHandler(MessageProtocol protocol, ServiceRegister serviceRegister) {
        this.protocol = protocol;
        this.serviceRegister = serviceRegister;
    }

    public MessageProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(MessageProtocol protocol) {
        this.protocol = protocol;
    }

    public ServiceRegister getServiceRegister() {
        return serviceRegister;
    }

    public void setServiceRegister(ServiceRegister serviceRegister) {
        this.serviceRegister = serviceRegister;
    }

    public byte[] handleRequest(byte[] data) throws Exception {

        // 1、解组消息
        SimpleRequest req = this.protocol.unmarshallingRequest(data);

        // 2、查找服务对象
        ServiceObject so = this.serviceRegister.getServiceObject(req.getServiceName());

        SimpleResponse rsp;

        if (so == null) {
            rsp = new SimpleResponse(SimpleStatus.NOT_FOUND);
        } else {
            try {
                // 3、反射调用对应的过程方法
                Method method = so.getClazz().getMethod(req.getMethod(), req.getParameterTypes());
                Object returnValue = method.invoke(so.getObj(), req.getParameters());
                rsp = new SimpleResponse(SimpleStatus.SUCCESS);
                rsp.setReturnValue(returnValue);
            } catch (Exception ex) {
                rsp = new SimpleResponse(SimpleStatus.ERROR);
                rsp.setException(ex);
            }
        }

        return this.protocol.marshallingResponse(rsp);
    }


}
