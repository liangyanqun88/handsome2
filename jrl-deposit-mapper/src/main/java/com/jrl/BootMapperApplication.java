package com.jrl;

import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//@SpringBootApplication
public class BootMapperApplication {

	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream =  BootMapperApplication.class.getClassLoader().getResourceAsStream("application-mapper.yml");
		properties.load(inputStream);

		SpringApplication app = new SpringApplication(BootMapperApplication.class);
		app.setDefaultProperties(properties);
		app.run(args);
//		SpringApplication.run(BootMapperStartApplication.class, args);
	}
}
