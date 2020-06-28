package com.yadeah.minichat.server;

import com.yadeah.minichat.common.constant.system.ConfigKey;
import com.yadeah.minichat.common.utils.ConfigUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TCPServer {

    public void init() throws InterruptedException {
        // 主线程组，用于接收客户连接，不作处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程组，处理主线程组的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)   // 绑定线程组
                .channel(NioServerSocketChannel.class)  // 设置通讯模式为NIO
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.SO_BACKLOG, ConfigUtils.getIntProperty(ConfigKey.TCP_QUEUE_SIZE)) // 设置缓冲区
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)      // 心跳检测
                .childOption(ChannelOption.SO_RCVBUF, 256 * 1024)   // 设置发送缓冲区
                .childOption(ChannelOption.SO_SNDBUF, 256 * 1024)   // 设置接收缓冲区
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new TcpServerInitializer());

        ChannelFuture cf = serverBootstrap.bind(ConfigUtils.getIntProperty(ConfigKey.TCP_PORT)).sync();
        cf.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
