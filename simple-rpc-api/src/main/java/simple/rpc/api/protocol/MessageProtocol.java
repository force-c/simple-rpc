package simple.rpc.api.protocol;

import java.io.Serializable;

/**
 * 消息传输协议
 *
 * @author guochuang
 * @version 6.3
 * @date 2022/05/19 17:52
 */
public class MessageProtocol implements Serializable {


	private String version;

	private long serialization;

	private Object message;


}
