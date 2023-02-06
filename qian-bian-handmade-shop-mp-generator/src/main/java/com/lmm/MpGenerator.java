package com.lmm;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;
import java.util.Scanner;

public class MpGenerator {
    public static final String PROJECT_PATH = System.getProperty("user.dir") + "\\qian-bian-handmade-shop-mp-generator";
    public static final String URL_PREFIX = "jdbc:mysql://192.168.159.130:48100/";
    public static final String URL_SUFFIX = "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "qianbian";

    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        System.out.println("请输入数据库名");
        String dbName = new Scanner(System.in).nextLine();
        System.out.println(PROJECT_PATH);
        FastAutoGenerator.create(URL_PREFIX + dbName + URL_SUFFIX, USER_NAME, PASSWORD)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("芝麻")   // 作者
                            .fileOverride()    // 覆盖已生成文件
                            .outputDir(PROJECT_PATH + "/src/main/java")     // 指定输出目录
                            .enableSwagger()   // 开启 swagger 的 api
                            .disableOpenDir()  // 禁止打开输出目录
                            .dateType(DateType.TIME_PACK) // 时间策略
                            .commentDate("yyyy-MM-dd") //注释日期
                            .build();
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.lmm")   // 父包名
                            .moduleName("")                 // 父包模块名
                            .entity("entity")               // Entity 包名
                            .service("service")             // service 包名
                            .serviceImpl("service.impl")    //
                            .mapper("mapper")               //
                            .xml("mapper.xml")              // MapperXML 包名
                            .controller("controller")       //
                            .pathInfo(Collections.singletonMap(OutputFile.xml, PROJECT_PATH + "/src/main/resources/com/lmm/mapper/")) // 自定义某个文件的路径
                            .build();
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addTablePrefix("%")
                            .entityBuilder()  // 实体类配置
                            .enableTableFieldAnnotation()
                            .idType(IdType.ASSIGN_ID)
                            .controllerBuilder() // controller 接口配置
                            .enableHyphenStyle() // 开启驼峰转连字符
                            .enableRestStyle()
                            .serviceBuilder() // service 配置
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder()  //mapper 配置
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                            .build();
                })
                // 模板配置
                .templateEngine(new VelocityTemplateEngine())
                .templateConfig(builder -> builder.controller("/templates/controller.java").build())
                .execute();
    }
}
