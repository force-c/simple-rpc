package cn.simple.rpc.config;

import cn.simple.rpc.client.ClientProxyFactory;
import cn.simple.rpc.client.discovery.ZookeeperServiceDiscoverer;
import cn.simple.rpc.client.net.NettyNetClient;
import cn.simple.rpc.common.protocol.JavaSerializeMessageProtocol;
import cn.simple.rpc.common.protocol.MessageProtocol;
import cn.simple.rpc.properties.SimpleRpcProperty;
import cn.simple.rpc.server.NettyRpcServer;
import cn.simple.rpc.server.RequestHandler;
import cn.simple.rpc.server.RpcServer;
import cn.simple.rpc.server.register.DefaultRpcProcessor;
import cn.simple.rpc.server.register.ServiceRegister;
import cn.simple.rpc.server.register.ZookeeperExportServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring boot 自动装配类
 *
 * @author guochuang
 * @version v1
 * @modifier guochuang
 * @date 2021/08/16 13:01:00
 */
@Configuration
public class AutoConfiguration {


    @Bean
    public DefaultRpcProcessor defaultRpcProcessor() {
        return new DefaultRpcProcessor();
    }

    @Bean
    public ClientProxyFactory clientProxyFactory(@Autowired SimpleRpcProperty simpleRpcProperty) {
        ClientProxyFactory clientProxyFactory = new ClientProxyFactory();

        //设置服务发现者
        clientProxyFactory.setServiceDiscoverer(new ZookeeperServiceDiscoverer(simpleRpcProperty.getRegisterAddress()));

        // 设置支持的协议
        Map<String, MessageProtocol> supportMessageProtocols = new HashMap<String, MessageProtocol>(){
            private static final long serialVersionUID = 8821994772780933649L;
            {
            put(simpleRpcProperty.getProtocol(), new JavaSerializeMessageProtocol());
            }
        };
        clientProxyFactory.setSupportMessageProtocols(supportMessageProtocols);

        // 设置网络层实现
        clientProxyFactory.setNetClient(new NettyNetClient());
        return clientProxyFactory;
    }

    @Bean
    public ServiceRegister serviceRegister(@Autowired SimpleRpcProperty simpleRpcProperty) {
        return new ZookeeperExportServiceRegister(
                simpleRpcProperty.getRegisterAddress(),
                simpleRpcProperty.getServerPort(),
                simpleRpcProperty.getProtocol()
        );
    }

    @Bean
    public RequestHandler requestHandler(@Autowired ServiceRegister serviceRegister) {
        return new RequestHandler(new JavaSerializeMessageProtocol(), serviceRegister);
    }

    @Bean
    public RpcServer rpcServer(@Autowired SimpleRpcProperty simpleRpcProperty,
                               @Autowired RequestHandler requestHandler) {
        return new NettyRpcServer(
                simpleRpcProperty.getServerPort(),
                simpleRpcProperty.getProtocol(),
                requestHandler);
    }

    @Bean
    public SimpleRpcProperty gcRpcProperty() {
        return new SimpleRpcProperty();
    }


}
