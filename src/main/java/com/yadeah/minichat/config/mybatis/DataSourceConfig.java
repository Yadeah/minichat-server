package com.yadeah.minichat.config.mybatis;

import com.yadeah.minichat.common.utils.log.LogUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@MapperScan(basePackages = "com.yadeah.minichat.mapper", sqlSessionTemplateRef = "SqlSessionTemplate")
public class DataSourceConfig {

    private static SqlSessionFactory sqlSessionFactory;

    protected SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            String resource = "config/mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(resource);
            } catch (IOException e) {
                LogUtils.error(e, "数据库初始化失败");
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "minichat");
        }
        return sqlSessionFactory;
    }

    @Bean(name = "DataSource")
    public DataSource setDataSource() {
        return getSqlSessionFactory().getConfiguration().getEnvironment().getDataSource();
    }

    @Bean(name = "TransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        bean.setConfiguration(getSqlSessionFactory().getConfiguration());
        return bean.getObject();
    }

    @Bean(name = "SqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
