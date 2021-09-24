package cn.simple.rpc.server.register;

/**
 *服务注册器，定义服务注册规范
 *
 * @author guochuang
 * @version v1
 * @date 2021/09/22 13:38:00
 */
public interface ServiceRegister {

    void register(ServiceObject so) throws Exception;

    ServiceObject getServiceObject(String name) throws Exception;
}
