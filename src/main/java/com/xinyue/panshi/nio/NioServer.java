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
public class NioServer {
    private static volatile boolean stopped;

    private static  int port = 8588;

    public static void main(String [] args ) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (!stopped){
            try {
                /**
                 *  当selector.select()被调用时，如果没有已经注册的事件到达，将会一直处于阻塞状态；直至有注册事件到达
                 *  实现原理：
                 */
                selector.select();
                Iterator<SelectionKey>  selectionKeys = selector.selectedKeys().iterator();
                while(selectionKeys.hasNext()){
                    SelectionKey selectionKey = selectionKeys.next();
                    selectionKeys.remove();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        StringBuilder message = new StringBuilder("Hello, ");

                        try {
                            /* 将客户端"jerry" 写入 buffer 写入数据时，
                             * limit和capacity相同，每写入一组数据，position会加1，直至position到达capacity的位置
                             * 或数据写入完毕，那么limit则指向最后position的数值
                             * 因此buffer.position() = 5 buffer.limit()= 1024
                             */
                            int length = channel.read(buffer);
                            System.out.println("初始化写入buffer时");
                            System.out.println("position:" + buffer.position() + "\t limit:"
                                    + buffer.limit());

                            if (length > 0) {
                                /*
                                 * 把limit设为当前position，先将limit 设置为当前position 然后把position设为0，
                                 * 一般在从Buffer读出数据前调用。limit 实际的
                                 */
                                buffer.flip();
                                System.out.println("buffer.flip()时");
                                System.out.println("position:" + buffer.position() + "\t limit:"
                                        + buffer.limit());
                                // 为了做测试位置因此只读了2个字节，实际上要读完才知道客户端发送了什么数据
                                byte[] bytes = new byte[2];
                                /*
                                 * 读取buffer 读取数据时，每读取一组数据，position会加1，
                                 * 读取到limit所在的位置即结束，如果缓冲区完全被数据充满，那么limit则等于capacity。
                                 * position + 2 = 2
                                 */
                                buffer.get(bytes);
                                // position + 1 = 3
                                buffer.get();
                                System.out.println("获取两个字节（两次get()方法调用）后");
                                System.out.println("position:" + buffer.position() + "\t limit:"
                                        + buffer.limit());
                                message.append(new String(bytes));
                                //回应客户端
                                doWrite(channel, message.toString());
                            } else {
                                selectionKey.cancel();
                                channel.close();
                            }
                            // selectionKey.interestOps(SelectionKey.OP_READ);
                        } catch (IOException i) {
                            // 如果捕获到该SelectionKey对应的Channel时出现了异常,即表明该Channel对于的Client出现了问题
                            // 所以从Selector中取消该SelectionKey的注册
                            selectionKey.cancel();
                            if (selectionKey.channel() != null) {
                                selectionKey.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e){

            }
        }
    }

    private static void doWrite(SocketChannel sc, String message) throws IOException{
        ByteBuffer byteBuffer = ByteBuffer.allocate(message.getBytes().length);
        byteBuffer.put(message.getBytes());
        byteBuffer.flip();
        sc.write(byteBuffer);
    }
}
