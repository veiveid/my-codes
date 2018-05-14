package com.test.big_data.demo1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

/**
 * java如何更快生成00000000～99999999的8位数字保存到文件中？
 */
public class Generate99999999 {
    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        long t = System.currentTimeMillis();
        // 打开文件
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("a.txt"), StandardCharsets.UTF_8))) {
            IntStream.range(0, 99999999 + 1) // 遍历
                    .mapToObj(String::valueOf) // 转为字符串
                    .map(s -> "00000000".substring(0, "00000000".length() - s.length()) + s + "\n") // 补0，换行
                    .forEach(s -> {
                        // 写入文件
                        try {
                            writer.write(s);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
            writer.flush();
        }
        System.out.println((System.currentTimeMillis() - t) + "ms");
    }
}