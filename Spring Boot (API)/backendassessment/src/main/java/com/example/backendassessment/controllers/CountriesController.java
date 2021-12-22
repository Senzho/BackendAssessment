package com.example.backendassessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendassessment.models.CountryModel;
import com.example.backendassessment.services.CountriesService;

@RestController
@RequestMapping("/countries")
public class CountriesController {
	@Autowired
	private CountriesService service;
	
	@GetMapping("/all")
	public List<CountryModel> getAll() {
		List<CountryModel> countries = this.service.getAll();
		return countries;
	}
	@GetMapping("/look/{id}")
	public CountryModel get(@PathVariable int id) {
		CountryModel country = this.service.getSingle(id);
		return country;
	}
	@PostMapping("/add")
	public CountryModel post(@RequestBody CountryModel country) {
		CountryModel newCountry = this.service.create(country);
		return newCountry;
	}
	@PutMapping("/up")
	public CountryModel put(@RequestBody CountryModel country) {
		CountryModel updatedCountry = this.service.update(country);
		return updatedCountry;
	}
	@DeleteMapping("/del/{id}")
	public CountryModel delete(@PathVariable int id) {
		CountryModel country = this.service.delete(id);
		return country;
	}
}
