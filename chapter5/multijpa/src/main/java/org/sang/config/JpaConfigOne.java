package org.sang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.sang.dao1",
    entityManagerFactoryRef = "entityManagerFactoryBeanOne",
    transactionManagerRef = "platformTransactionManagerOne")
public class JpaConfigOne {
    @Resource(name = "dsOne")
    DataSource dsOne;

    private final JpaProperties jpaProperties;

    public JpaConfigOne(JpaProperties jpaProperties) {
        this.jpaProperties = jpaProperties;
    }
    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanOne(
            EntityManagerFactoryBuilder builder){
        return builder.dataSource(dsOne)    //配置数据源
                .properties(jpaProperties.getProperties())  //设置JPA相关配置
                .packages("org.sang.model") //设置实体类所在的位置
                .persistenceUnit("pu1")     //配置持久化单元名，若项目只有一个EntityManager，则persistenceUnit可以省略，若有多个，则必须明确指定持久化单元名
                .build();
    }
    @Bean
    PlatformTransactionManager platformTransactionManagerOne(
            EntityManagerFactoryBuilder builder){
        LocalContainerEntityManagerFactoryBean factoryBean = entityManagerFactoryBeanOne(builder);
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
