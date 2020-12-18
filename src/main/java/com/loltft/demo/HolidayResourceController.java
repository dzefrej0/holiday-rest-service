package com.loltft.demo;

import com.loltft.demo.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
public class HolidayResourceController implements HolidayApiGateway{

	private HolidayService holidayService;

	@Autowired
	public HolidayResourceController( HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	private static final Logger log = LoggerFactory.getLogger(HolidayResourceApplication.class);

	@Override
	public GetHolidayDto getHoliday(String date, String countryOne, String countryTwo) {
		RestTemplate restTemplate = new RestTemplate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate dateOutput = LocalDate.parse(date, formatter);
		GetHolidayDto holidayToReturn;

		holidayToReturn = holidayService.getHolidayFromService(restTemplate, countryOne, countryTwo, dateOutput);

		log.info("response should be:");
		log.info("date :  " +holidayToReturn.getDate() + ";  name1  "+ holidayToReturn.getName1()+ ";  name2  "+ holidayToReturn.getName2());
		return  holidayToReturn;
	}
}
