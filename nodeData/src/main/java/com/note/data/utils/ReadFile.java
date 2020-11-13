package com.note.data.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    /***
     * 一行一行的读取文件中的数据
     * @param filePath 文件路径
     * @return
     */
    public static String readFileContent(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                stringBuffer.append(tempStr);
            }
            reader.close();
            if (stringBuffer.equals(null) || stringBuffer.equals("")) {
                return null;
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (stringBuffer.equals(null) || stringBuffer.equals("")) {
            return null;
        }
        return stringBuffer.toString();
    }
}
