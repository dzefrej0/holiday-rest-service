package com.loltft.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidayFilterDto {


	private LocalDate date;
	private String name;
	private String country;

}
