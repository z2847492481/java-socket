package com.zhq.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @DATE: 2024-02-03 18:35
 * @Author: zhq123
 */
@Slf4j
public class Demo1Client {
    public static void main(String[] args) throws IOException {
        // 创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("localhost", 8888);

        // 获取输入流和输出流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // 向服务器发送数据
        String requestData = "Hello from client!";
        outputStream.write(requestData.getBytes());
        outputStream.flush();
        log.info("Request sent to server.");

        // 读取服务器响应数据
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        String responseData = new String(buffer, 0, bytesRead);
        log.info("Received response from server: " + responseData);

        // 关闭连接
        socket.close();
    }
}
