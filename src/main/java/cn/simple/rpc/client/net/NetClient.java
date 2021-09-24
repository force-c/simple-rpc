package cn.simple.rpc.client.net;

import cn.simple.rpc.common.service.Service;

/**
 * 网络请求客户端，定义网络请求规范
 *
 * @author guochuang
 * @version v1
 * @date 2021/09/22 14:36:00
 */
public interface NetClient {
    byte[] sendRequest(byte[] data, Service service) throws InterruptedException;
}
