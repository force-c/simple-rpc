package simple.rpc.registry.simple;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guochuang
 * @version 6.3
 * @date 2022/05/19 18:42
 */
public class SimpleRegistry {

	private static final Map<String, ChannelHandlerContext> CTX_CONTAINER1 = new HashMap<>();

	private static final Map<ChannelHandlerContext, String> CTX_CONTAINER2 = new HashMap<>();

	@SuppressWarnings("unchecked")
	public static <T> T get(Object key) {

		if (key instanceof String) {
			return (T) CTX_CONTAINER1.get(key);
		}
		if (key instanceof ChannelHandlerContext) {
			return (T) CTX_CONTAINER2.get(key);
		}
		return null;
	}

	public static void put(String clientId, ChannelHandlerContext ctx) {
		CTX_CONTAINER1.put(clientId, ctx);
		CTX_CONTAINER2.put(ctx, clientId);
	}

}
