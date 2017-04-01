package com.alex.Config;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.alex")
@EnableJpaRepositories(basePackages = "com.alex.dal")
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class JPAConfig {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    @Profile("test")
    public DataSource dataSourceForTest() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();
    }

    @Bean(name = "jpaVendorAdapter")
    @Profile("test")
    public JpaVendorAdapter jpaVendorAdapterForTest() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.H2);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return adapter;
    }

    @Bean(name = "entityManagerFactory")
    @Profile("test")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryForTest(
            DataSource dataSourceForTest, JpaVendorAdapter jpaVendorAdapterForTest) {

        Properties props = new Properties();
        props.setProperty("hibernate.format_sql", String.valueOf(true));
        //props.setProperty("hibernate.hbm2ddl.auto", String.valueOf(""));
        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSourceForTest);
        emf.setPackagesToScan("com.alex.entity");
        emf.setJpaVendorAdapter(jpaVendorAdapterForTest);
        emf.setJpaProperties(props);

        return emf;
    }

    @Bean(name = "transactionManager")
    @Profile("test")
    public PlatformTransactionManager transactionManagerForTest(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean(name ="persistenceTranslation")
    @Profile("test")
    public BeanPostProcessor persistenceTranslationForTest() {
        return new PersistenceExceptionTranslationPostProcessor();
    }



    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.pass"));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {

        Properties props = new Properties();
        props.setProperty("hibernate.format_sql", String.valueOf(false));

        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.alex.entity");
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setJpaProperties(props);

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
