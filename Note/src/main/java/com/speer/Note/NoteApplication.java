package com.speer.Note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.speer.Note.config.JWTFilter;

@SpringBootApplication
public class NoteApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		
		FilterRegistrationBean registrationbean = new FilterRegistrationBean();
		registrationbean.setFilter(new JWTFilter());
		registrationbean.addUrlPatterns("/api/notes/*");
		return registrationbean;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(NoteApplication.class, args);
	}

}
