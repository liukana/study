package com.example.admin.accessibilityservicetest.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by liukan on 2018/7/21.
 */
public class EncrptFile {

    public static void main(String[] args) {

//        readFileBytes(new File(""));
    }

    private static byte[] readFileBytes(File file) throws IOException {
        byte[] arrayOfByte = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            int length = fileInputStream.read(arrayOfByte);
            if (length != -1) {
                byteArrayOutputStream.write(arrayOfByte, 0, length);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
