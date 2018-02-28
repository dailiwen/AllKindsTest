package com.example.dailiwen.allkindstest.activity.Netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 *
 * @author NewBies
 * @date 2018/2/24
 */
public class MessageDecoder extends ByteToMessageDecoder {

   
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        if (in.readableBytes() < 4) {
//        	System.out.println(in.readInt());
//            return;
//        }

        in.markReaderIndex();
//        //判断协议类型
//        int infoType = in.readInt();
        byte[] body = new byte[in.readableBytes()];
        in.readBytes(body);
        System.out.println(new String(body, "utf-8"));

//        switch (infoType){
//            case 1:
//            	System.out.println(in.readableBytes());
//            	in.release();
//                break;
//            case 2:
//            	System.out.println(in.readableBytes());
//            	in.release();
//                break;
//            default:
//            	System.out.println(in.readableBytes());
//            	System.out.println("infoType: " +infoType);
//                ctx.close();
//                return;
//        }
    }
}
