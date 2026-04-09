package ru.bitkov.springdatabase.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class UserHibernateConfig {

    private final Environment environment;



    public UserHibernateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setJdbcUrl(environment.getRequiredProperty("db.url"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));

        return dataSource;
    }

    @Bean
    protected Properties properties(){

        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("ru.bitkov.springdatabase.model");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(properties());

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager(){

        JpaTransactionManager jp = new JpaTransactionManager();
        jp.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return jp;
    }

}
