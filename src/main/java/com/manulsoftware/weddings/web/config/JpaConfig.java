package com.manulsoftware.weddings.web.config;

import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("ee.omx.mhub2.core.facade")
@EnableTransactionManagement
@Configuration
@org.springframework.context.annotation.PropertySource("classpath:hibernate.properties")
public class JpaConfig {

	private static final Logger logger = LoggerFactory.getLogger(JpaConfig.class);
	private static final String DATASOURCE_CONTEXT = "java:/comp/env/jdbc/mhubDS";

	private static final String HIBERNATE = "hibernate";

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "ee.omx.mhub2.core.facade", "ee.omx.mhub2.core.data" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties(env));

		return em;
	}

	@Bean
	public DataSource dataSource() {
		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);

			return datasource;
		} catch (NamingException ex) {
			logger.error("Cannot get connection (naming fail): ", ex);
		}
		return null;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties(Environment env) {
		Properties properties = new Properties();
		for (Iterator<PropertySource<?>> it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext();) {
			PropertySource<?> propertySource = (PropertySource<?>) it.next();
			if (propertySource instanceof MapPropertySource) {
				MapPropertySource mapPropertySource = (MapPropertySource) propertySource;

				for (String key : mapPropertySource.getSource().keySet()) {
					if (key != null && key.startsWith(HIBERNATE)) {
						final Object value = mapPropertySource.getSource().get(key);
						if (value != null) {
							properties.setProperty(key, value.toString());
						}
					}
				}
			}
		}

		return properties;
	}
}
