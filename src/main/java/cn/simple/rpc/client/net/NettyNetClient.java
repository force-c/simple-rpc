package cn.simple.rpc.client.net;

import cn.simple.rpc.client.net.handler.SendHandler;
import cn.simple.rpc.common.service.Service;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guochuang
 * @version v1
 * @date 2021/09/22 16:19:00
 */
public class NettyNetClient implements NetClient{

    private static final Logger logger = LoggerFactory.getLogger(NettyNetClient.class);

    @Override
    public byte[] sendRequest(byte[] data, Service service) throws InterruptedException {
        String[] addInfoArray = service.getAddress().split(":");
        String serverAddress = addInfoArray[0];
        String serverPort = addInfoArray[1];

        SendHandler sendHandler = new SendHandler(data);
        byte[] respData;

        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(sendHandler);
                        }
                    });
            bootstrap.connect(serverAddress, Integer.parseInt(serverPort))
                    .sync();
            respData = (byte[]) sendHandler.rspData();
            logger.info("SendRequest get reply: {}", respData);
        } finally {
            group.shutdownGracefully();
        }

        return respData;
    }



}
