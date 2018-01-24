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
public class TcpServerHandler implements Runnable {
    private final Socket socket;


    public TcpServerHandler (final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            sayHello();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void sayHello() throws IOException {
        StringBuilder message = new StringBuilder("Hello, ");
        byte[] buffer = new byte[1024];
        int length;
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        // 读取输入流并且返回
        while ( -1 != (length = inputStream.read(buffer))){
            message.append(new String(buffer, 0, length));
        }
        outputStream.write(message.toString().getBytes());
        socket.shutdownOutput();
    }
}
