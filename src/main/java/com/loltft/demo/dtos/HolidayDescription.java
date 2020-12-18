package com.loltft.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidayDescription {

	private String description;

	private int id;
}
