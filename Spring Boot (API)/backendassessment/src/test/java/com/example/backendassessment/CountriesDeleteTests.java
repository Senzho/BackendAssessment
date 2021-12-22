package com.example.backendassessment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backendassessment.models.CountryModel;
import com.example.backendassessment.services.CountriesService;

@SpringBootTest
public class CountriesDeleteTests {
	@Autowired
	private CountriesService service;
	
	@Test
	public void deletePositive() {
		List<CountryModel> countries = this.service.getAll();
		CountryModel country = countries.get(countries.size() - 1);
		CountryModel deleteCountry = this.service.delete(country.getId());
		assertEquals(country.getId(), deleteCountry.getId());
	}
	@Test
	public void deleteNegative() {
		CountryModel deleteCountry = this.service.delete(10000);
		assertEquals(-1, deleteCountry.getId());
	}
}
