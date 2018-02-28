package com.example.dailiwen.allkindstest.activity.Netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author dailiwen
 * @date 2018/02/27
 */

public class NettyClientBootstrap {
    private int port;
    private String host;
    private SocketChannel socketChannel;

    public NettyClientBootstrap(int port, String host) throws Exception {
        this.host = host;
        this.port = port;
        start();
    }

    private void start() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        //保持连接
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        //有数据立即发送
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        //绑定处理group
        bootstrap.group(eventLoopGroup);
        bootstrap.remoteAddress(this.host, this.port);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                //初始化编码器，解码器，处理器
                socketChannel.pipeline().addLast(new MessageDecoder(),new MessageEncoder(), new NettyClientHandler());
            }
        });
        //进行连接
        ChannelFuture future = bootstrap.connect(this.host, this.port).sync();
        //判断是否连接成功
        if (future.isSuccess()) {
            socketChannel = (SocketChannel) future.channel();
            System.out.println("connect server success");
        }
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
