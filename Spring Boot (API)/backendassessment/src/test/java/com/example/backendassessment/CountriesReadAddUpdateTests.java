package com.example.backendassessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backendassessment.models.CountryModel;
import com.example.backendassessment.services.CountriesService;

@SpringBootTest
class CountriesReadAddUpdateTests {
	@Autowired
	private CountriesService service;

	//Tests for getting data:
	@Test
	public void listNotNull() {
		List<CountryModel> countries = this.service.getAll();
		assertNotNull(countries);
	}
	@Test
	public void listHasItems() {
		List<CountryModel> countries = this.service.getAll();
		assertTrue(countries.size() > 0);
	}
	@Test
	public void countryExists() {
		int id = this.service.getAll().get(0).getId();
		CountryModel country = this.service.getSingle(id);
		assertEquals(id, country.getId());
	}
	@Test
	public void countryNotExists() {
		CountryModel country = this.service.getSingle(10000);
		assertEquals(-1, country.getId());
	}
	//Tests for adding and updating
	@Test
	public void addingPositive() {
		CountryModel country = new CountryModel();
		country.setCapital("Washington DC");
		country.setLanguageId(2);
		country.setName("United States of America");
		country.setPopulation(30000746);
		CountryModel addedCountry = this.service.create(country);
		assertTrue(addedCountry.getId() == (this.service.getNewId() - 1) && addedCountry.getName().equals(country.getName()));
	}
	@Test
	public void addingNegative() {
		CountryModel country = new CountryModel();
		country.setCapital("New Delhi");
		//Language doesn't exists
		country.setLanguageId(3);
		country.setName("India");
		country.setPopulation(28080746);
		CountryModel addedCountry = this.service.create(country);
		assertEquals(-1, addedCountry.getId());
	}
	@Test
	public void updatingPositive() {
		CountryModel country = this.service.getSingle(2);
		country.setPopulation(1000000);
		CountryModel updatedCountry = this.service.update(country);
		assertEquals(country.getPopulation(), updatedCountry.getPopulation());
	}
	@Test
	public void updatingNegative() {
		CountryModel country = new CountryModel();
		CountryModel updatedCountry = this.service.update(country);
		assertEquals(-1, updatedCountry.getId());
	}
}
