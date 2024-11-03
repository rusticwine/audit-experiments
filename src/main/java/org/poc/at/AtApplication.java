package org.poc.at;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

//@ComponentScan(basePackages = {"org.poc.at.model", "org.poc.at.service", "org.poc.at.repos"})
@EnableJpaRepositories(considerNestedRepositories = true)
@SpringBootApplication
@EnableJpaAuditing
//@EnableEnversRepositories//(considerNestedRepositories = true)
public class AtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtApplication.class, args);
	}

	////////////////////////////////////////////////////////////////
//connection being closed - solution: https://stackoverflow.com/questions/36258677/how-to-access-auditreaderfactory-in-spring-boot-application
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	AuditReader auditReader() {
		return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
	}

	@Bean
	public Hibernate5Module hibernateModule() {
		return new Hibernate5Module();
	}
//https://stackoverflow.com/questions/10739314/returning-entities-in-rest-api-with-spring
//https://stackoverflow.com/questions/45110371/no-string-argument-constructor-factory-method-to-deserialize-from-string-value
//	@Override
//	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//		for (HttpMessageConverter converter : converters) {
//			if (converter instanceof MappingJackson2HttpMessageConverter) {
//				((MappingJackson2HttpMessageConverter)converter).setObjectMapper(objectMapper());
//			}
//		}
//	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		return objectMapper;
	}
}