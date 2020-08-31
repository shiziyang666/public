package com.demo.service.impl;

import com.demo.service.ReadFileService;
import com.demo.util.ReadFile;
import com.demo.util.RegUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ReadFileServiceImpl implements ReadFileService {

    /***
     * 读取每一行中的数字
     */
    @Override
    public void readFile() {
        List<String> list = ReadFile.readFileContent("C:\\data\\applogs\\cat\\cat_20200602.log");
        //第一行的所有数字
        String str1 = list.get(0);
        System.out.println(getNum(str1));
        //第二行的所有数字
        String str2 = list.get(1);
        System.out.println(getNum(str2));
        //第三行的所有数字
        String str3 = list.get(2);
        System.out.println(getNum(str3));
    }

    //筛选出数字
    public String getNum(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException("参数str不能为空");
        }
        StringBuffer number = new StringBuffer("");

        String[] strArray = str.split("");
        for (String string : strArray) {
            if (!StringUtils.isEmpty(string) && RegUtils.isNumberText(string)) {
                number.append(string + ",");
            }
        }
        return number.toString();
    }
}
