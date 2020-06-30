import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.net.SocketAddress;

/**
 * 描述:
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-03-16 上午10:28
 */
public class DiscardServerHandler  extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //将Object强转成ByteBuf(引用计数对象) ((ByteBuf)msg).release();
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                Channel channel = ctx.channel();
                SocketAddress socketAddress = channel.remoteAddress();
                System.out.println(socketAddress+"连接了你");
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //cause.printStackTrace();
        System.out.println(ctx.channel().remoteAddress()+"强迫关闭了连接");
        System.out.println("跑完了......肥噶!");
        ctx.close();
    }

}
