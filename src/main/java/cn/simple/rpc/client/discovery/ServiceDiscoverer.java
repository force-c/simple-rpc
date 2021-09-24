package cn.simple.rpc.client.discovery;

import cn.simple.rpc.common.service.Service;

import java.util.List;

/**
 * 服务发现抽象类，定义服务发现规范
 *
 * @author guochuang
 * @version v1
 * @date 2021/09/22 14:34:00
 */
public interface ServiceDiscoverer {
    List<Service> getServices(String name);
}
