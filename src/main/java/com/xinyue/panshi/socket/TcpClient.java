package com.xinyue.panshi.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author hxy
 * @time 2018/1/15
 * @desc
 */
public class TcpClient {

    private static  int port = 8488;

    public static void main(String [] args ) throws IOException {
        Socket socket = new Socket("localhost",port);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        System.out.println(getSayHelloMessage("Jerry", socket, inputStream, outputStream));
    }

    private static String getSayHelloMessage(String name, Socket socket, InputStream inputStream, OutputStream outputStream) throws IOException {
        outputStream.write(name.getBytes());
        socket.shutdownOutput();
        StringBuilder result = new StringBuilder();
        byte [] buffer = new byte[1024];
        int length;
        // 读取返回的流并且返回
        while ( -1 != (length = inputStream.read(buffer))){
            result.append(new String(buffer, 0, length));
        }
        return result.toString();
    }
}
