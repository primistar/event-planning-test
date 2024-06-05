package com.software.eventplanning;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * @author <a href="mailto:zkq1026@gmail.com">keqin</a>
 * @description
 * @date 2023年07月19日 23:37
 */
public class CodeGen {
    public static void main(String[] args) {
        String url
                = "jdbc:mysql://127.0.0.1:3307/activityplanning?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT"
                + "%2B8";
        String userName = "root";
        String password = "root";
        String projectPath = System.getProperty("user.dir");
        String outputDir = projectPath + "/src/main/java2";
        String outputXmlDir = projectPath + "/src/main/resources/mapper2";
        //templateEngine.templateFilePath("/templates/entity.java");
        FastAutoGenerator.create(url, userName, password)
                .globalConfig(builder -> {
                    builder.author("<a href=\"mailto:zkq1026@gmail.com\">keqin</a>") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()
                            .outputDir(outputDir); // 指定输出目录

                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.software.eventplanning") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputXmlDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    //builder.addInclude("eb_user_rcvaddress") // 设置需要生成的表名
                    builder.addInclude("activities") // 设置需要生成的表名
                            //.addTablePrefix("eb_") // 设置过滤表前缀
                            .entityBuilder()
                            .enableLombok()
                            .enableFileOverride()
                            .controllerBuilder()
                            .enableFileOverride()
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .enableFileOverride()
                            .formatMapperFileName("%sMapper")
                            .serviceBuilder()
                            .enableFileOverride()
                            .formatServiceFileName("%sService");
                })
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
