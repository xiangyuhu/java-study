package com.xinyue.panshi.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hxy
 * @time 2018/1/15
 * @desc
 */
public class SocketServer {
    private static volatile boolean stopped;

    private static  int port = 8488;

    public static void main(String [] args ) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        ServerSocket serverSocket = new ServerSocket(port);

        while (!stopped){
            // accept方法是阻塞的，直到接收到一个连接返回一个客户端的Socket对象实例
            Socket socket = serverSocket.accept();
            // 采用线程池处理
            executorService.submit(new TcpServerHandler(socket));
        }
    }
}
