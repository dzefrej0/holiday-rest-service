package com.loltft.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class HolidayResourceApplication {

	private static final Logger log = LoggerFactory.getLogger(HolidayResourceApplication.class);




	public static void main(String[] args) throws Exception {
		SpringApplication.run(HolidayResourceApplication.class, args);




		HolidayResourceController HRC = new HolidayResourceController();



		System.out.println("testy");
	}


}


