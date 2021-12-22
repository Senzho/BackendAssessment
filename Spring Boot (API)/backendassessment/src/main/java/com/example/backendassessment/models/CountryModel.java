package com.example.backendassessment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class CountryModel {
	@Id
	private Integer id;
	private String name;
	private String capital;
	private Integer population;
	@Column(name = "language_id")
	private Integer languageId;
	
	public CountryModel() {
		this.id = -1;
		this.population = -1;
		this.languageId = -1;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
}
