package com.example.dailiwen.allkindstest.activity.Mina;

import com.example.dailiwen.allkindstest.entity.SfkInfo;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author dailiwen
 * @date 2018/2/5
 */
public class SfkInfoDecoder implements MessageDecoder {

    private final Charset charset;

    /**
     * 保存上下文
     */
    private final AttributeKey attributeKey = new AttributeKey(getClass(), "contex");

    public SfkInfoDecoder() {
        this(Charset.forName("UTF-8"));
    }

    public SfkInfoDecoder(Charset charset) {
        this.charset = charset;
    }

    /**
     * 该方法用于判断对于传送过来的数据，是否应该使用该解码器
     */
    @Override
    public MessageDecoderResult decodable(IoSession session, IoBuffer buffer) {
        if (buffer.remaining() < 4) {
            return MessageDecoderResult.NEED_DATA;
        } else {
            int infoType = buffer.getInt();
            if (infoType == 1) {
                return MessageDecoderResult.OK;
            } else {
                return MessageDecoderResult.NOT_OK;
            }
        }
    }

    /**
     * 进行解码操作
     */
    @Override
    public MessageDecoderResult decode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput output) throws Exception {
        SfkInfo skfInfo = new SfkInfo();
        Context context = getContext(session);
        CharsetDecoder charsetDecoder = charset.newDecoder();
        int matchCount = context.getMatchCount();
        int currentLine = context.getCurrentLine();
        IoBuffer ioBuffer = context.getIoBuffer();
        String storeId = context.getStoreId();
        String terminalId = context.getTerminalId();
        String serviceSocket = context.getServiceSocket();
        int fireSize = context.getFireSize();
        int windSize = context.getWindSize();
        int condenseSize = context.getCondenseSize();
        String bootTime = context.getBootTime();


        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            matchCount++;
            ioBuffer.put(b);
            if (currentLine < 8 && b == 10) {
                switch (currentLine) {
                    case 0:
						matchCount = 0;
						ioBuffer.clear();
						break;
                    case 1:
                        //输入输出反转
                        ioBuffer.flip();
                        storeId = ioBuffer.getString(matchCount, charsetDecoder);
                        storeId = storeId.substring(0, storeId.length() - 1);
                        matchCount = 0;
                        ioBuffer.clear();
                        context.setStoreId(storeId);
                        break;
                    case 2:
                        ioBuffer.flip();
                        terminalId = ioBuffer.getString(matchCount, charsetDecoder);
                        terminalId = terminalId.substring(0, terminalId.length() - 1);
                        matchCount = 0;
                        ioBuffer.clear();
                        context.setTerminalId(terminalId);
                        break;
                    case 3:
                        ioBuffer.flip();
                        serviceSocket = ioBuffer.getString(matchCount, charsetDecoder);
                        serviceSocket = serviceSocket.substring(0, serviceSocket.length() - 1);
                        matchCount = 0;
                        ioBuffer.clear();
                        context.setServiceSocket(serviceSocket);
                        break;
                    case 4:
                        ioBuffer.flip();
                        fireSize = ioBuffer.getInt(matchCount);
                        ioBuffer.clear();
                        context.setFireSize(fireSize);
                        break;
                    case 5:
                        ioBuffer.flip();
                        windSize = ioBuffer.getInt(matchCount);
                        ioBuffer.clear();
                        context.setWindSize(windSize);
                        break;
                    case 6:
                        ioBuffer.flip();
                        condenseSize = ioBuffer.getInt(matchCount);
                        ioBuffer.clear();
                        context.setCondenseSize(condenseSize);
                        break;
                    case 7:
                        ioBuffer.flip();
                        bootTime = ioBuffer.getString(matchCount, charsetDecoder);
                        ioBuffer.clear();
                        context.setBootTime(bootTime);
                        break;
                    default:
                        break;
                }
                currentLine++;
            } else {
                context.setMatchCount(matchCount);
                context.setCurrentLine(currentLine);
            }
        }
        if (currentLine == 8) {
            skfInfo.setStoreId(storeId);
            skfInfo.setTerminalId(terminalId);
            skfInfo.setServiceSocket(serviceSocket);
            skfInfo.setFireSize(fireSize);
            skfInfo.setWindSize(windSize);
            skfInfo.setCondenseSize(condenseSize);
            skfInfo.setBootTime(bootTime);
            output.write(skfInfo);
            context.reset();
            return MessageDecoderResult.OK;
        }

        return MessageDecoderResult.NEED_DATA;
    }

    /**
     * 解码操作完成
     */
    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput output) throws Exception {
    }

    private Context getContext(IoSession session) {
        Context context = (Context) session.getAttribute(attributeKey);
        if (context == null) {
            context = new Context();
            session.setAttribute(attributeKey, context);
        }
        return context;
    }

    private static class Context {

        private final IoBuffer ioBuffer;
        /**
         * 当前读取到第几行了
         */
        private int currentLine;
        /**
         * 当前行已经读取了多少个字节了
         */
        private int matchCount;
        /**
         * 门店ID
         */
        private String storeId;
        /**
         * 终端ID
         */
        private String terminalId;
        /**
         * 服务器套接字
         */
        private String serviceSocket;
        /**
         * 火力大小
         */
        private int fireSize;
        /**
         * 风力大小
         */
        private int windSize;
        /**
         * 冷凝程度
         */
        private int condenseSize;
        /**
         * 开机时长
         */
        private String bootTime;

        public Context() {
            ioBuffer = IoBuffer.allocate(100).setAutoExpand(true);
        }

        public IoBuffer getIoBuffer() {
            return ioBuffer;
        }

        public int getCurrentLine() {
            return currentLine;
        }

        public void setCurrentLine(int currentLine) {
            this.currentLine = currentLine;
        }

        public int getMatchCount() {
            return matchCount;
        }

        public void setMatchCount(int matchCount) {
            this.matchCount = matchCount;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getTerminalId() {
            return terminalId;
        }

        public void setTerminalId(String terminalId) {
            this.terminalId = terminalId;
        }

        public String getServiceSocket() {
            return serviceSocket;
        }

        public void setServiceSocket(String serviceSocket) {
            this.serviceSocket = serviceSocket;
        }

        public int getFireSize() {
            return fireSize;
        }

        public void setFireSize(int fireSize) {
            this.fireSize = fireSize;
        }

        public int getWindSize() {
            return windSize;
        }

        public void setWindSize(int windSize) {
            this.windSize = windSize;
        }

        public int getCondenseSize() {
            return condenseSize;
        }

        public void setCondenseSize(int condenseSize) {
            this.condenseSize = condenseSize;
        }

        public String getBootTime() {
            return bootTime;
        }

        public void setBootTime(String bootTime) {
            this.bootTime = bootTime;
        }

        public void reset() {
            this.ioBuffer.clear();
            this.currentLine = 0;
            this.matchCount = 0;
            this.storeId = "";
            this.terminalId = "";
            this.serviceSocket = "";
            this.fireSize = 0;
            this.windSize = 0;
            this.condenseSize = 0;
            this.bootTime = "";
        }
    }

}
