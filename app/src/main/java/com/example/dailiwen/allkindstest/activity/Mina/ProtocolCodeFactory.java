package com.example.dailiwen.allkindstest.activity.Mina;

import com.example.dailiwen.allkindstest.entity.SfkInfo;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * @author NewBies
 */
public class ProtocolCodeFactory extends DemuxingProtocolCodecFactory {
	
	public ProtocolCodeFactory(boolean service){
		//如果是服务端使用，那么需要添加服务端的编码器和客户端的各种解码器
		if(service){
			super.addMessageDecoder(SfkInfoDecoder.class);
		}
		//如果是客户端使用，那么需要添加客户端的编码器和服务端的各种解码器
		else{
			super.addMessageEncoder(SfkInfo.class, SfkInfoEncoder.class);
		}
	}
}
