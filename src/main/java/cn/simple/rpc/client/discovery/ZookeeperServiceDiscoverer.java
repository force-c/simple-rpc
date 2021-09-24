package cn.simple.rpc.client.discovery;

import cn.simple.rpc.common.serializer.ZookeeperSerializer;
import cn.simple.rpc.common.service.Service;
import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.simple.rpc.common.constants.SimpleConstant.PATH_DELIMITER;
import static cn.simple.rpc.common.constants.SimpleConstant.UTF_8;
import static cn.simple.rpc.common.constants.SimpleConstant.ZK_SERVICE_PATH;

/**
 * Zookeeper服务发现者，定义以Zookeeper为注册中心的服务发现细则
 *
 * @author guochuang
 * @version v1
 * @date 2021/09/22 15:57:00
 */
public class ZookeeperServiceDiscoverer implements ServiceDiscoverer{

    private ZkClient zkClient;

    public ZookeeperServiceDiscoverer(String zkAddress) {
        zkClient = new ZkClient(zkAddress);
        this.zkClient.setZkSerializer(new ZookeeperSerializer());
    }

    @Override
    public List<Service> getServices(String name) {
        String servicePath = ZK_SERVICE_PATH + PATH_DELIMITER + name + "/service";
        List<String> children = zkClient.getChildren(servicePath);
        return Optional.ofNullable(children).orElse(new ArrayList<>()).stream().map(str -> {
            String deCh = null;
            try {
                deCh = URLDecoder.decode(str, UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return JSON.parseObject(deCh, Service.class);
        }).collect(Collectors.toList());
    }

}
