package org.cloud.xue.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig2
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 10:15:29
 * @Version 1.0
 **/
@Configuration
@MapperScan(basePackages = "org.cloud.xue.mapper.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DataSourceConfig2 {
    @Bean("db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource getDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource")DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/db2/*.xml"));
        return bean.getObject();
    }

    @Bean("db2SqlSessionTemplate")
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
