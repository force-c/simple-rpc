package cn.simple.rpc.common.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Java序列化消息协议
 *
 * @author guochuang
 * @version v1
 * @date 2021/09/22 11:30:00
 */
public class JavaSerializeMessageProtocol implements MessageProtocol{

    private byte[] serialize(Object obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.writeObject(obj);
        oout.writeObject(obj);
        return bout.toByteArray();
    }


    @Override
    public byte[] marshallingRequest(SimpleRequest req) throws Exception {
        return this.serialize(req);
    }

    @Override
    public SimpleRequest unmarshallingRequest(byte[] data) throws Exception {
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
        return (SimpleRequest) inputStream.readObject();
    }

    @Override
    public byte[] marshallingResponse(SimpleResponse rsp) throws Exception {
        return this.serialize(rsp);
    }

    @Override
    public SimpleResponse unmarshallingResponse(byte[] data) throws Exception {
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
        return (SimpleResponse) inputStream.readObject();
    }
}
