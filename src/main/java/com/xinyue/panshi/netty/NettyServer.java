package com.xinyue.panshi.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author hxy
 * @time 2018/1/16
 * @desc
 */
public class NettyServer {

    private static  int port = 8688;

    public static void main(String [] args ) throws  InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.weakCachingResolver(getClass().getClassLoader())));
                            // socketChannel.pipeline().addLast(new ObjectEncoder());
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128);
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally{
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
