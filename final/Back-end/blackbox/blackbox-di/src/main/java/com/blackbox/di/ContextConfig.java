package com.blackbox.di;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.blackbox.api.dao.IDialogDao;
import com.blackbox.api.dao.IEventDao;
import com.blackbox.api.dao.IGroupDao;
import com.blackbox.api.dao.IMessageDao;
import com.blackbox.api.dao.IUserDao;
import com.blackbox.api.service.IDialogService;
import com.blackbox.api.service.IEventService;
import com.blackbox.api.service.IGroupService;
import com.blackbox.api.service.IMessageService;
import com.blackbox.api.service.IUserService;
import com.blackbox.api.util.ITokenRepository;
import com.blackbox.dao.DialogDao;
import com.blackbox.dao.EventDao;
import com.blackbox.dao.GroupDao;
import com.blackbox.dao.MessageDao;
import com.blackbox.dao.UserDao;
import com.blackbox.service.DialogService;
import com.blackbox.service.EventService;
import com.blackbox.service.GroupService;
import com.blackbox.service.MessageService;
import com.blackbox.service.UserService;
import com.blackbox.util.TokenRepository;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.blackbox" })
@PropertySource({ "classpath:jdbc.properties" })
public class ContextConfig {

	@Autowired
	private Environment env;

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(
				new String[] { "com.blackbox.beans", "com.blackbox.dao", "com.blackbox.service", "com.blackbox.util" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans",
				env.getProperty("hibernate.enable_lazy_load_no_trans"));

		return hibernateProperties;
	}

	@Bean(name = "userDao")
	public IUserDao getUserDao() {
		return new UserDao();
	}

	@Bean(name = "groupDao")
	public IGroupDao getGroupDao() {
		return new GroupDao();
	}

	@Bean(name = "eventDao")
	public IEventDao getEventDao() {
		return new EventDao();
	}

	@Bean(name = "dialogDao")
	public IDialogDao getDialogDao() {
		return new DialogDao();
	}

	@Bean(name = "messageDao")
	public IMessageDao getMessageDao() {
		return new MessageDao();
	}

	@Bean(name = "userService")
	public IUserService getUserService() {
		return new UserService();
	}

	@Bean(name = "groupService")
	public IGroupService getGroupService() {
		return new GroupService();
	}

	@Bean(name = "eventService")
	public IEventService getEventService() {
		return new EventService();
	}

	@Bean(name = "dialogService")
	public IDialogService getDialogService() {
		return new DialogService();
	}

	@Bean(name = "messageService")
	public IMessageService getMessageService() {
		return new MessageService();
	}

	@Bean(name = "tokenRepository")
	public ITokenRepository getTokenRepository() {
		return new TokenRepository();
	}
}
