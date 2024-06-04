package com.software.eventplanning.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Swagger配置组件
 */
@Configuration   //标记该类为 Spring 配置类。
@EnableKnife4j   //启用 Knife4j
@EnableSwagger2WebMvc   //启用 Swagger2 Web MVC
@Import(BeanValidatorPluginsConfiguration.class)    //导入 Bean 验证插件配置
public class Knife4jConfig {

    //一个私有的 OpenApiExtensionResolver 字段，用于解析 OpenAPI 扩展
    private final OpenApiExtensionResolver openApiExtensionResolver;

    //@Autowired 构造函数
    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    //是否开启swagger，通常在正式环境中需要关闭，可根据springboot的多环境配置进行设置
    Boolean swaggerEnabled = true;

    //从配置文件中获取服务器端口号
    @Value("${server.port}")
    private String port;

    //创建一个名为 “front” 的 Docket 对象，用于配置 API 文档。
    @Bean("front")
    public Docket create1RestApis() {
        return new Docket(DocumentationType.SWAGGER_2)
               // .groupName("front")
               // .host(domain)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.software.eventplanning"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(frontPathsAnt()) //只监听
                .build()
                .securitySchemes(security())
                .securityContexts(securityContexts())
                //                .globalOperationParameters(pars) // 针对单个url的验证 如果需要的话
                .pathMapping("/");
    }


    private Predicate<String> frontPathsAnt() {
        return PathSelectors.ant("/api/**");
    }

    private Predicate<String> publicPathsAnt() {
        return PathSelectors.ant("/v1/api/public/**");
    }

    private List<SecurityScheme> security() {
        return new ArrayList<>(Arrays.asList(new ApiKey("Authorization", "Authorization", "header")));
        //return Lists.newArrayList();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("活动安排规划系统 API")
                .description("活动安排规划系统 API")
                .termsOfServiceUrl("https://www.xxx.com")
                .version("1.0.0").build();
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> res = new ArrayList<>();
        res.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build());
        return res;
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> res = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "Authorization");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        res.add(new SecurityReference("Authorization", authorizationScopes));
        return res;
    }
}
