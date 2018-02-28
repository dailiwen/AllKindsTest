package com.example.dailiwen.allkindstest.activity.Mina;

import com.example.dailiwen.allkindstest.entity.SfkInfo;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 *
 * @author NewBies
 * @date 2018/1/31
 */
public class SfkInfoEncoder implements MessageEncoder<SfkInfo> {

    private final Charset charset;

    public SfkInfoEncoder(){
        this(Charset.forName("UTF-8"));
    }

    public SfkInfoEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession ioSession, SfkInfo sfkInfo, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        IoBuffer buffer = IoBuffer.allocate(100);
        //通过字符集实例得到编码器
        CharsetEncoder ce = charset.newEncoder();
        buffer.putInt(sfkInfo.getINFO_TYPE());
        //10对应的字符就是/n，使用该字符判断一条信息是否读取完毕
        buffer.put((byte) 10);
        buffer.putString(sfkInfo.getStoreId(), ce);
        buffer.put((byte) 10);
        buffer.putString(sfkInfo.getTerminalId(), ce);
        buffer.put((byte) 10);
        buffer.putString(sfkInfo.getServiceSocket(), ce);
        buffer.put((byte) 10);
        buffer.putInt(sfkInfo.getFireSize());
        buffer.put((byte) 10);
        buffer.putInt(sfkInfo.getWindSize());
        buffer.put((byte) 10);
        buffer.putInt(sfkInfo.getCondenseSize());
        buffer.put((byte) 10);
        buffer.putString(sfkInfo.getBootTime(), ce);
        buffer.put((byte) 10);
        //输入输出翻转
        buffer.flip();
        //输出缓冲区
        protocolEncoderOutput.write(buffer);
    }
}
