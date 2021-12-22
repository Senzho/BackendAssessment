package com.example.backendassessment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.backendassessment.models.CountryModel;

@Repository
public interface CountriesRepository extends CrudRepository<CountryModel, Integer> {

}
