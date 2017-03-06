package com.vaadin.demo;

import com.vaadin.demo.service.DBInitService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
public class PatientDashboardApplication extends JpaBaseConfiguration{

    protected PatientDashboardApplication(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider) {
        super(dataSource, properties, jtaTransactionManagerProvider);
    }

    public static void main(String[] args) {
        SpringApplication.run(PatientDashboardApplication.class, args);
    }

    @Bean
    CommandLineRunner init(DBInitService s) {
        return (String[] str) -> {
            s.initDatabase();
        };
    }

    @Override
    protected org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        return Collections.singletonMap("eclipselink.weaving", "false");
    }

}
