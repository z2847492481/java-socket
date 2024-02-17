package com.zhq.nio;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @DATE: 2024-02-17 12:30
 * @Author: zhq123
 */
@Slf4j
public class ChannelAndBufferDemo2 {

    public static void main(String[] args) {
        // 使用guava工具类读取resource目录下的文件
        URL textUrl = Resources.getResource("1.txt");

        try {
            Path path = Paths.get(textUrl.toURI());
            // 获取channel
            try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
                // 创建缓存
                ByteBuffer buffer = ByteBuffer.allocate(10);
                // 循环读取数据
                while (channel.read(buffer) != -1) {
                    // buffer切换成读模式
                    buffer.flip();
                    // 处理buffer中的数据
                    while(buffer.hasRemaining()) {
                        log.info("data: {}", (char) buffer.get());
                    }
                    // 切换成写模式
                    buffer.clear();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
