package com.loltft.demo;

import com.loltft.demo.dtos.GetHolidayDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/getHoliday", method = RequestMethod.GET)
public interface HolidayApiGateway {


	@RequestMapping("/HavingMutualCountries")
	GetHolidayDto getHoliday(@RequestParam(value = "date", defaultValue = "20190101") String date,
			@RequestParam(value = "countryOne", defaultValue = "pl") String countryOne,
			@RequestParam(value = "countryTwo", defaultValue = "de") String countryTwo);
}
