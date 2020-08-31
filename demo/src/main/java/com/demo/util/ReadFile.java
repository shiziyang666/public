package com.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    /***
     * 一行一行的读取文件中的数据
     * @param filePath 文件路径
     * @return
     */
    public static List<String> readFileContent(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        List<String> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                list.add(tempStr);
            }
            reader.close();
            return list;
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
        return list;
    }
}
