package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	//Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("http://editor2.swagger.io")
			.allowedMethods("PUT", "DELETE", "GET", "POST")
			.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
			//.exposedHeaders("header1", "header2")
			.allowCredentials(false).maxAge(3600);
	}
}