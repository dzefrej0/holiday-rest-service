package com.loltft.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HolidayResourceApplication {

	public static void main(String[] args){
		SpringApplication.run(HolidayResourceApplication.class, args);

		HolidayService holidayService = new HolidayService();
		HolidayResourceController HRC = new HolidayResourceController(holidayService);
	}


}


