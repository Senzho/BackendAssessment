package com.example.backendassessment.services;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;

import com.example.backendassessment.models.CountryModel;
import com.example.backendassessment.repositories.CountriesRepository;

@Service
public class CountriesService {
	@Autowired
	private CountriesRepository repository;
	
	private CountryModel save(CountryModel country) {
		CountryModel savedCountry = (CountryModel) this.repository.save(country);
		return savedCountry;
	}
	
	public int getNewId() {
		int newId;
		List<CountryModel> countries = this.getAll();
		if (countries.isEmpty()) {
			newId = 1;
		} else {
			int lastId = countries.get(countries.size() - 1).getId();
			newId = lastId + 1;
		}
		return newId;
	}
	public List<CountryModel> getAll() {
		List<CountryModel> countries = (List<CountryModel>) this.repository.findAll();
		countries = countries.stream().sorted(Comparator.comparingInt(CountryModel::getId)).collect(Collectors.toList());
		return countries;
	}
	public CountryModel getSingle(int id) {
		CountryModel country;
		try {
			country = this.repository.findById(id).orElseThrow();
		} catch (NoSuchElementException e) {
			country = new CountryModel();
		}
		return country;
	}
	public CountryModel create (CountryModel country) {
		CountryModel newCountry;
		country.setId(this.getNewId());
		try {
			newCountry = this.save(country);
		} catch (DataIntegrityViolationException e) {
			newCountry = new CountryModel();
		}
		return newCountry;
	}
	public CountryModel update (CountryModel country) {
		CountryModel updatedCountry;
		if (this.repository.existsById(country.getId())) {
			updatedCountry = this.save(country);
		} else {
			updatedCountry = new CountryModel();
		}
		return updatedCountry;
	}
	public CountryModel delete (int id) {
		CountryModel country;
		if (this.repository.existsById(id)) {
			country = this.getSingle(id);
			this.repository.deleteById(id);
		} else {
			country = new CountryModel();
		}
		return country;
	}
}
