package com.ilife.ssa.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorConfig {
    /**
     * 跳转到错误页面
     * 注意这个Bean如果放在@EnableWebSecurity注解的类下会导致启动报错
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN,"/error/403");
        factory.addErrorPages(errorPage403);
        return factory;
    }
}
