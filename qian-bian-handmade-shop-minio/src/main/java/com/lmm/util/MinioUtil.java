package com.lmm.util;

import cn.hutool.core.date.DateUtil;
import com.lmm.exception.QianBianException;

import java.time.LocalDateTime;

public class MinioUtil {
    public static final String EXTENSION_SEPARATOR = ".";

    public static String getFileExtension(String fileName) {
        if (fileName == null) {
            throw new QianBianException("文件名为空");
        }
        int indexOfSeparator = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        if (indexOfSeparator == -1) {
            // 文件没有拓展名
            throw new QianBianException("文件没有拓展名!");
        }
        return fileName.substring(indexOfSeparator);
    }

    public static String todayFolder() {
        return DateUtil.format(LocalDateTime.now(), "yyyy/MM/dd/");
    }
}
