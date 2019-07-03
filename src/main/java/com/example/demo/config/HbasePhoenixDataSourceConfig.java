package com.example.demo.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.demo.mybatisext.PhoenixSqlInjector;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource(value="classpath:application.properties")
@MapperScan(basePackages = HbasePhoenixDataSourceConfig.PACKAGE,sqlSessionFactoryRef = HbasePhoenixDataSourceConfig.HBASEPHOENIX_SQL_SESSION_FACTORY)
@Order(80)
public class HbasePhoenixDataSourceConfig {
    static final String HBASEPHOENIX_SQL_SESSION_FACTORY = "hbasePhoenixSqlSessionFactory";
    static final String PACKAGE = "com.example.demo.hbase";
    static final String MAPPER_LOCATION = "classpath:mapper/hbase/*.xml";

    @Autowired
    PaginationInterceptor paginationInterceptor;

    @Autowired
    ISqlInjector phoenixSqlInjector;

    @Bean(name = "hbasePhoenixDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource hbasePhoenixDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(name = "hbasePhoenixTransactionManager")
    public DataSourceTransactionManager hbasePhoenixTransactionManager(@Qualifier("hbasePhoenixDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = HBASEPHOENIX_SQL_SESSION_FACTORY)
    public SqlSessionFactory hbasePhoenixSqlSessionFactory(@Qualifier("hbasePhoenixDataSource") DataSource hbasePhoenixDataSource)
            throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(hbasePhoenixDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(HbasePhoenixDataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setPlugins(new Interceptor[]{paginationInterceptor});
        GlobalConfig defaults = GlobalConfigUtils.defaults();
        defaults.setSqlInjector(phoenixSqlInjector);
        sessionFactory.setGlobalConfig(defaults);
        return sessionFactory.getObject();
    }


}
