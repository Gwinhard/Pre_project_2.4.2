package web.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:DataBase.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("DataBase.driver"));
        dataSource.setUrl(env.getProperty("DataBase.url"));
        dataSource.setUsername(env.getProperty("DataBase.username"));
        dataSource.setPassword(env.getProperty("DataBase.password"));
        return dataSource;
    }

    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        containerEntityManagerFactoryBean.setDataSource(getDataSource());
        HibernateJpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
        containerEntityManagerFactoryBean.setJpaVendorAdapter(adaptor);
        Properties props = new Properties();
        props.getProperty("DataBase.driver");
        props.getProperty("hibernate.show_sql");
        props.getProperty("hibernate.hbm2ddl.auto");
        containerEntityManagerFactoryBean.setJpaProperties(props);
        containerEntityManagerFactoryBean.setPackagesToScan("web");
        return containerEntityManagerFactoryBean;
    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(getDataSource());
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

}
