package com.loltft.demo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidaysDto {


	private String name;
	private LocalDate date;
	private Date observed;
	private boolean publicc;
	private String country;
	private String uuid;
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<WeekDayDto> weekday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonSerialize(using= DateSerializer.class)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	@JsonSerialize(using= DateSerializer.class)
	public Date getObserved() {
		return observed;
	}

	public void setObserved(Date observed) {
		this.observed = observed;
	}

	public boolean isPublicc() {
		return publicc;
	}

	public void setPublicc(boolean publicc) {
		this.publicc = publicc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<WeekDayDto> getWeekday() {
		return weekday;
	}

	public void setWeekday(List<WeekDayDto> weekday) {
		this.weekday = weekday;
	}
}
