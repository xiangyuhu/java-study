package com.xinyue.panshi.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author hxy
 * @time 2018/1/16
 * @desc
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf)msg;
        byte [] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String reqBody = new String(req, "UTF-8");
        System.out.println("server recieve body: " + reqBody);
        StringBuilder body = new StringBuilder("Hello, ").append(reqBody);
        ByteBuf resp = Unpooled.copiedBuffer(body.toString().getBytes());
        // 或者直接调用 ctx.writeAndFlush(msg) 这样channelReadComplete 方法就不必flush;
        ctx.write(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
