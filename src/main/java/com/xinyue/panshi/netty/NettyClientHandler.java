package com.xinyue.panshi.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author hxy
 * @time 2018/1/16
 * @desc
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] req = new byte[result.readableBytes()];
        result.readBytes(req);
        System.out.println("recieve:" + new String(req));
    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "jerry";
        System.out.println("成功连接服务端正在发送请求");
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
    }
}
