package com.yadeah.minichat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TcpServerHandler extends SimpleChannelInboundHandler<TcpMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TcpMessage tcpMessage) throws Exception {
        switch (tcpMessage.getType()) {
            case LOGIN:
                SessionSocketHolder.addChannel(tcpMessage.getAccountId(), (NioSocketChannel)channelHandlerContext.channel());
                break;
            case CHAT:
                // TODO
                break;
            default:

        }

    }
}
