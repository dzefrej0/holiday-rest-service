//ten kod ssie kurwa pałe!!!

//po pierwsze brak lambd
// po drugie nie wykorzystujesz springa! a gdzie jakieś beany? jakieś autoviery?
//brak testów




package com.loltft.demo;

import com.loltft.demo.dtos.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
public class HolidayResourceController {

	private static final Logger log = LoggerFactory.getLogger(HolidayResourceApplication.class);

	@GetMapping("/greeting")
	public GetHolidayDto giveHolidayResponse(@RequestParam(value = "date", defaultValue = "20190101") String date,
			                                 @RequestParam(value = "countryOne", defaultValue = "pl") String countryOne,
			                                 @RequestParam(value = "countryTwo", defaultValue = "de") String countryTwo)  throws Exception{

		RestTemplate restTemplate = new RestTemplate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate dateOutput = LocalDate.parse(date, formatter);
		GetHolidayDto holidayToReturn;

		holidayToReturn=getHolidayFromService(restTemplate, countryOne, countryTwo, dateOutput);
		log.info("response should be:");
		log.info("date :  " +holidayToReturn.getDate() + ";  name1  "+ holidayToReturn.getName1()+ ";  name2  "+ holidayToReturn.getName2());
		return  holidayToReturn;

	}

	public GetHolidayDto getHolidayFromService(RestTemplate restTemplate, String countryName1, String countryName2, LocalDate date) {

		final String uri = "https://holidayapi.com/v1/holidays?key={key}&country={country}&year={year}&month={month}";

		Map<String, String> params = new HashMap<>();
		String year = String.valueOf(date.getYear());
		String month = String.valueOf(date.getMonth().getValue());
        params.put("month", month);
		params.put("country", ""+countryName1+","+countryName2+"");
		params.put("key", "73412c57-0443-4435-9348-ede1cd2b608f");
		params.put("year", year);
		log.info("parameters:      "+ "country : "+ params.get("country")  + "     month :   " + params.get("month") + "         year "  + params.get("year")  );
		ResponseEntity<HolidaysDtoDto> response = restTemplate.getForEntity(uri, HolidaysDtoDto.class, params);

		List <LocalDate>allDatesList = new ArrayList<>();

		for (int i = 0; response.getBody().getHolidays().size()>i; i++) {
			allDatesList.add( response.getBody().getHolidays().get(i).getDate());
		}

		Map<LocalDate,String > dateCountryMap = new HashMap<>();
		for (int i = 0; response.getBody().getHolidays().size()>i; i++) {
			dateCountryMap.put(response.getBody().getHolidays().get(i).getDate(), response.getBody().getHolidays().get(i).getName());
		}

		List <LocalDate> duplicatedDates = findDuplicates1(allDatesList);

		Collections.sort(duplicatedDates, Comparator.comparing(obj -> obj.atStartOfDay()));

		List<String> holidayListtoReturn = matchHolidayNames(dateCountryMap, duplicatedDates);
		GetHolidayDto dto = new GetHolidayDto();

		dto.setDate(duplicatedDates.get(0));
		dto.setName1(holidayListtoReturn.get(0));
		dto.setName2(holidayListtoReturn.get(1));

			return dto;
		}

	public List <LocalDate> findDuplicates1 (List <LocalDate> list) {

		List <LocalDate> listoutput = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).equals(list.get(j))) {
					listoutput.add(list.get(i));
				}
			}
		}
		return listoutput;
	}

	public List<String> matchHolidayNames (Map<LocalDate,String > dateCountryMap, List <LocalDate> duplicatedDates){

		List<String> holidayListtoReturn = new ArrayList<>();

		holidayListtoReturn.add(0, dateCountryMap.get(duplicatedDates.get(0)));
		holidayListtoReturn.add(1, dateCountryMap.get(duplicatedDates.get(0)));

		return holidayListtoReturn;
	}
}
