package com.loltft.demo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class HolidaysDtoDto {

	private String status;
	private String warning;
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<RequestsDto> requests;
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<HolidaysDto> holidays;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}


	public List<HolidaysDto> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<HolidaysDto> holidays) {
		this.holidays = holidays;
	}

	public List<RequestsDto> getRequests() {
		return requests;
	}

	public void setRequests(List<RequestsDto> requests) {
		this.requests = requests;
	}
}
