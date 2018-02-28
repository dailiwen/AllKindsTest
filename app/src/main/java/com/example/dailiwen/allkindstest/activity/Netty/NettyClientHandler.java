package com.example.dailiwen.allkindstest.activity.Netty;

import android.app.DownloadManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author dailiwen
 * @date 2018/02/27
 */

public class NettyClientHandler extends SimpleChannelInboundHandler<RequestInfo>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestInfo msg) throws Exception {
        System.out.println("我是客户端，我接受到了：" + msg.getBody());
        RequestInfo req = new RequestInfo();
        req.setSequence(msg.getSequence());
        req.setType(msg.getType());
        if (2 == msg.getType()) {
            req.setBody("client");
            ctx.channel().writeAndFlush(req);
        } else if (3 == msg.getType()) {
            req.setBody("zpksb");
            ctx.channel().writeAndFlush(req);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("通道读取完毕！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(null != cause) cause.printStackTrace();
        if(null != ctx) ctx.close();
    }
}
