package cn.simple.rpc.common.serializer;

import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.nio.charset.StandardCharsets;

/**
 * @author guochuang
 * @version v1
 * @date 2021/09/22 11:52:00
 */
public class ZookeeperSerializer implements ZkSerializer {
    @Override
    public byte[] serialize(Object o) {
        return String.valueOf(o).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object deserialize(byte[] bytes) {
        return new String(bytes,StandardCharsets.UTF_8);
    }
}
