package com.zhq.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @DATE: 2024-02-03 18:35
 * @Author: zhq123
 */
@Slf4j
public class Demo1Server {

    public static void main(String[] args) throws IOException {
        // 创建服务器Socket，监听指定端口
        ServerSocket serverSocket = new ServerSocket(8888);

        log.info("Server started. Waiting for client connection...");

        // 等待客户端连接
        Socket socket = serverSocket.accept();
        log.info("Client connected.");

        // 获取输入流和输出流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // 读取客户端发送的数据
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        String requestData = new String(buffer, 0, bytesRead);
        log.info("Received data from client: " + requestData);

        // 向客户端发送响应数据
        String responseData = "Hello from server!";
        outputStream.write(responseData.getBytes());
        outputStream.flush();

        log.info("Response sent to client.");

        // 关闭连接
        socket.close();
        serverSocket.close();
    }
}
