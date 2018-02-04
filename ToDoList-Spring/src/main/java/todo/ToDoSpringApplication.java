package todo;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import todo.model.*;
import todo.persistence.PersistenceXStream;
import todo.controller.configuration.*;
@SpringBootApplication
public class ToDoSpringApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ToDoSpringApplication.class, args);
	}
	
	@Bean
	public TaskList getTodo() {
		return PersistenceXStream.initializeToDo(PersistenceXStream.getFilename());
	}

	
	@Autowired
	private AndroidProperties androidProperties;

	@Autowired
	private WebFrontendProperties webFrontendProperties;
	// Enable CORS globally
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Allow web client
				String frontendUrl = "http://" + webFrontendProperties.getIp() + ":" + webFrontendProperties.getPort();
				// Allow android client
				String androidUrl = "http://" + androidProperties.getIp() + ":" + androidProperties.getPort();
				// For debug purposes, allow connecting  from localhost as well
				registry.addMapping("/**").allowedOrigins(frontendUrl, androidUrl, "http://localhost:8087", "http://127.0.0.1:8087");
			}
		};
	}
}
