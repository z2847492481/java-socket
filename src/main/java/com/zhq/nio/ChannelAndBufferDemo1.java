package com.zhq.nio;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @DATE: 2024-02-16 17:07
 * @Author: zhq123
 */
@Slf4j
public class ChannelAndBufferDemo1 {

    public static void main(String[] args) throws IOException {

        // 使用guava工具类读取resource目录下的文件
        URL textUrl = Resources.getResource("1.txt");

        // 转成fileChannel
        FileChannel fileChannel = new FileInputStream(textUrl.getPath()).getChannel();

        // 新建的是写模式
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        // 将channel中的内容写入buffer中
        fileChannel.read(byteBuffer);

        // 切换到读模式
        byteBuffer.flip();

        // 循环打印出buffer中的内容
        while(byteBuffer.hasRemaining()) {
            log.info("receive: {}", (char) byteBuffer.get());
        }
    }
}
