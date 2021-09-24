package cn.simple.rpc.server.register;

import cn.simple.rpc.common.serializer.ZookeeperSerializer;
import cn.simple.rpc.common.service.Service;
import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;

import static cn.simple.rpc.common.constants.SimpleConstant.PATH_DELIMITER;
import static cn.simple.rpc.common.constants.SimpleConstant.UTF_8;
import static cn.simple.rpc.common.constants.SimpleConstant.ZK_SERVICE_PATH;

/**
 * @author guochuang
 * @version v1
 * @date 2021/09/22 14:13:00
 */
public class ZookeeperExportServiceRegister extends DefaultServiceRegister implements ServiceRegister{

    private ZkClient client;

    /**
     * ZK客户端
     * @param zkAddress
     * @param port
     * @param protocol
     */
    public ZookeeperExportServiceRegister(String zkAddress, Integer port, String protocol) {
        client = new ZkClient(zkAddress);
        client.setZkSerializer(new ZookeeperSerializer());
        this.port = port;
        this.protocol = protocol;
    }

    /**
     * 服务注册
     * @param so
     * @throws Exception
     */
    public void register(ServiceObject so) throws Exception {
        super.register(so);
        Service service = new Service();
        String host = InetAddress.getLocalHost().getHostAddress();
        String address = host + ":" + port;
        service.setAddress(address);
        service.setName(so.getClazz().getName());
        service.setProtocol(protocol);
        this.exportService(service);
    }

    /**
     * 服务暴露
     * @param serviceResource
     */
    private void exportService(Service serviceResource) {
        String serviceName = serviceResource.getName();
        String uri = JSON.toJSONString(serviceResource);
        try {
            uri = URLEncoder.encode(uri, UTF_8);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        String servicePath = ZK_SERVICE_PATH + PATH_DELIMITER + serviceName + "/service";
        if (!client.exists(servicePath)) {
            client.createPersistent(servicePath, true);
        }
        String uriPath = servicePath + PATH_DELIMITER + uri;
        if (client.exists(uriPath)) {
            client.delete(uriPath);
        }
        client.createEphemeral(uriPath);
    }
}
