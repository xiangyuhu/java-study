package com.xinyue.panshi.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author hxy
 * @time 2018/1/15
 * @desc
 */
public class NioClient {
    private static volatile boolean stopped;
    private static  String  serverIp = "localhost";

    private static  int port = 8588;

    public static void main(String [] args ) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.connect(new InetSocketAddress(serverIp, port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (!stopped){
            try {
                selector.select();
                Iterator<SelectionKey>  selectionKeys = selector.selectedKeys().iterator();
                while(selectionKeys.hasNext()){
                    SelectionKey selectionKey = selectionKeys.next();
                    selectionKeys.remove();
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (selectionKey.isConnectable()) {
                        // 是否还在连接当中
                        if (channel.isConnectionPending()) {
                        }
                        // finishConnect()方法会阻塞到结束并返回是否成功
                        if (channel.finishConnect()) {
                            channel.configureBlocking(false);
                            channel.register(selector, SelectionKey.OP_READ);
                            doWrite(channel, "jerry");
                        }
                    } else if (selectionKey.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int length = channel.read(buffer);
                        StringBuilder message = new StringBuilder();
                        if (length > 0){
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            message .append(new String(bytes));
                            stopped = true;
                        }else if(length < 0) {
                            //对端链路关闭
                            selectionKey.channel();
                            channel.close();
                        }
                        System.out.println(message);
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                }
            } catch (IOException e){

            }
        }
    }

    private  static void doWrite(SocketChannel sc, String req) throws IOException{
        ByteBuffer byteBuffer = ByteBuffer.allocate(req.getBytes().length);
        byteBuffer.put(req.getBytes());
        byteBuffer.flip();
        sc.write(byteBuffer);
    }
}
