package com.codegym.service;

import com.codegym.DAO.CountryDAO;

import java.util.List;

public class CountryService {
    CountryDAO countryDAO = new CountryDAO();
    public List<String> getAllCountries(){
        return countryDAO.getAllCountries();
    }
}
