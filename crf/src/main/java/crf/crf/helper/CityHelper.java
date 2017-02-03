package crf.crf.helper;

import crf.crf.pojo.City;

public class CityHelper {

	public static City parseCity(String[] fields) throws NullPointerException, NumberFormatException{
		City city = new City();
		
		city.setIbgeId(Integer.parseInt(fields[0]));
		city.setUf(fields[1]);
		city.setName(fields[2]);
		city.setCapital(Boolean.parseBoolean(fields[3]));
		city.setLon(Float.parseFloat(fields[4]));
		city.setLat(Float.parseFloat(fields[5]));
		city.setNoAccents(fields[6]);
		city.setAlternativeName(fields[7]);
		city.setMicroRegion(fields[8]);
		city.setMesoRegion(fields[9]);
		
		return city;
	}
}
