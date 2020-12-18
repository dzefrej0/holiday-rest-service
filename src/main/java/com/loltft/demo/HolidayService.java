package com.loltft.demo;

import com.loltft.demo.dtos.GetHolidayDto;
import com.loltft.demo.dtos.HolidaysDtoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class HolidayService {

	private static final Logger log = LoggerFactory.getLogger(HolidayResourceApplication.class);

	@Value("${api-key}")
	private String key;

	public GetHolidayDto getHolidayFromService(RestTemplate restTemplate, String countryName1, String countryName2, LocalDate date) {

		final String uri = "https://holidayapi.com/v1/holidays?key={key}&country={country}&year={year}&month={month}";

		Map<String, String> params = new HashMap<>();
		String year = String.valueOf(date.getYear());
		String month = String.valueOf(date.getMonth().getValue());
		params.put("month", month);
		params.put("country", ""+countryName1+","+countryName2+"");
		params.put("key", key);
		params.put("year", year);
		log.info("parameters:      "+ "country : "+ params.get("country")  + "     month :   " + params.get("month") + "         year "  + params.get("year")  );
		ResponseEntity<HolidaysDtoDto> response = restTemplate.getForEntity(uri, HolidaysDtoDto.class, params);

		List<LocalDate> allDatesList = new ArrayList<>();

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
