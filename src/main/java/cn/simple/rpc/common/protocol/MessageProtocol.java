package cn.simple.rpc.common.protocol;

/**
 * 消息协议，定义编组请求、解组请求、编组响应、解组响应规范
 *
 * @author guochuang
 * @version v1
 * @date 2021/09/22 11:30:00
 */
public interface MessageProtocol {

    /**
     * 编组请求
     *
     * @param req           请求信息
     * @return              请求字节数组
     * @throws Exception    编组异常
     */
    byte[] marshallingRequest(SimpleRequest req) throws Exception;

    /**
     *
     * 解组请求
     *
     * @param data          请求字节数组
     * @return              解组请求
     * @throws Exception    解组请求异常
     */
    SimpleRequest unmarshallingRequest(byte[] data) throws Exception;


    /**
     * 编组响应
     *
     * @param rsp           响应信息
     * @return              响应字节数组
     * @throws Exception    编组响应异常
     */
    byte[] marshallingResponse(SimpleResponse rsp) throws Exception;

    /**
     * 解组响应
     * @param data          响应字节数组
     * @return              响应信息
     * @throws Exception    解组响应异常
     */
    SimpleResponse unmarshallingResponse(byte[] data) throws Exception;
}
