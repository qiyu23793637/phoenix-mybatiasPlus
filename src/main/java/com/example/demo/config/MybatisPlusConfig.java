package com.example.demo.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.example.demo.mybatisext.PhoenixSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setDialectClazz("com.example.demo.mybatisext.PhoenixDialect");
    }


    @Bean
    public ISqlInjector sqlInjector() {
        return new PhoenixSqlInjector();
    }
}
