package com.yummynoodlebar.config;

import java.sql.SQLException;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yummynoodlebar.persistence.repository.CustomersRepository;
import com.yummynoodlebar.persistence.repository.OrdersRepository;
import com.yummynoodlebar.persistence.repository.ProductRepository;
import com.yummynoodlebar.persistence.repository.ReviewsRepository;
import com.yummynoodlebar.persistence.services.CustomerPersistenceService;
import com.yummynoodlebar.persistence.services.CustomerPersistenceServiceImpl;
import com.yummynoodlebar.persistence.services.OrderPersistenceEventHandler;
import com.yummynoodlebar.persistence.services.OrderPersistenceService;
import com.yummynoodlebar.persistence.services.ProductPersistenceService;
import com.yummynoodlebar.persistence.services.ProductPersistenceServiceImpl;
import com.yummynoodlebar.persistence.services.ReviewPersistenceService;
import com.yummynoodlebar.persistence.services.ReviewPersistenceServiceImpl;

// {!begin transactions}
@Configuration
@EnableJpaRepositories(basePackages = "com.yummynoodlebar.persistence.repository", includeFilters = @ComponentScan.Filter(value = {
		OrdersRepository.class, CustomersRepository.class,
		ProductRepository.class, ReviewsRepository.class }, type = FilterType.ASSIGNABLE_TYPE))
@EnableTransactionManagement
public class JPAConfiguration {
	// {!end transactions}

	private static Random random = new Random();

	public static int getRandomDeliveryDays() {
		return random.nextInt(8) + 2;
	}

	public static int getRandomInStock() {
		return random.nextInt(30) + 1;
	}

	@Bean
	public DataSource dataSource() throws SQLException {

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.yummynoodlebar.persistence.domain");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public ProductPersistenceService menuPersistenceService(
			ProductRepository productRepository) {
		return new ProductPersistenceServiceImpl(productRepository);
	}

	@Bean
	public OrderPersistenceService orderPersistenceservice(
			OrdersRepository orderRepository) {
		return new OrderPersistenceEventHandler(orderRepository);
	}

	@Bean
	public CustomerPersistenceService customerPersistenceService(
			CustomersRepository customersRepository) {
		return new CustomerPersistenceServiceImpl(customersRepository);
	}

	@Bean
	ReviewPersistenceService reviewPersistenceService(
			ReviewsRepository reviewRepo) {
		return new ReviewPersistenceServiceImpl(reviewRepo);
	}
}