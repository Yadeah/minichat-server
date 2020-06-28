package com.yadeah.minichat.server;

import com.yadeah.minichat.common.constant.system.ConfigKey;
import com.yadeah.minichat.common.utils.ConfigUtils;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 心跳检测
        int idleTime = ConfigUtils.getIntProperty(ConfigKey.TCP_IDLE_TIME);
        socketChannel.pipeline().addLast(new IdleStateHandler(idleTime, idleTime, 0));
        socketChannel.pipeline().addLast(new TcpServerHandler());
    }
}
