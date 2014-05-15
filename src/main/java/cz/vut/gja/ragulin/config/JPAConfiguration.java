package cz.vut.gja.ragulin.config;

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
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cz.vut.gja.ragulin.persistence.repository.CustomersRepository;
import cz.vut.gja.ragulin.persistence.repository.OrdersRepository;
import cz.vut.gja.ragulin.persistence.repository.ProductRepository;
import cz.vut.gja.ragulin.persistence.repository.ReviewsRepository;
import cz.vut.gja.ragulin.persistence.services.CustomerPersistenceService;
import cz.vut.gja.ragulin.persistence.services.CustomerPersistenceServiceImpl;
import cz.vut.gja.ragulin.persistence.services.OrderPersistenceEventHandler;
import cz.vut.gja.ragulin.persistence.services.OrderPersistenceService;
import cz.vut.gja.ragulin.persistence.services.ProductPersistenceService;
import cz.vut.gja.ragulin.persistence.services.ProductPersistenceServiceImpl;
import cz.vut.gja.ragulin.persistence.services.ReviewPersistenceService;
import cz.vut.gja.ragulin.persistence.services.ReviewPersistenceServiceImpl;

// {!begin transactions}
@Configuration
@EnableJpaRepositories(basePackages = "cz.vut.gja.ragulin.persistence.repository", includeFilters = @ComponentScan.Filter(value = {
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
		// EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		// return builder.setType(EmbeddedDatabaseType.H2).build();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost/postgres");
		dataSource.setUsername("zhenia");

		return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("cz.vut.gja.ragulin.persistence.domain");
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