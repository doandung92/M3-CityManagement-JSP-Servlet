package com.codegym.service;

import com.codegym.DAO.CityDAO;
import com.codegym.model.City;

import java.util.List;

public class CityService {
    CityDAO cityDAO = new CityDAO();
    public List<City> getAllCities(){
        return cityDAO.getAllCities();
    }
    public boolean add(City city){
        return cityDAO.add(city);
    }
    public City getCityById(int id){
        return cityDAO.getCityById(id);
    }
    public boolean save(City city){
        return cityDAO.save(city);
    }
    public boolean delete(int id){
        return cityDAO.delete(id);
    }
    public boolean validateCity(City city){
        if (city.getName() == null || city.getName().equals("")) return false;
        if (city.getCountry() == null || city.getCountry().equals("")) return false;
        if (city.getArea()<=0) return false;
        if (city.getPopulation()<=0) return false;
        if (city.getGdp()<=0) return false;
        return city.getDescription() != null && !city.getDescription().equals("");
    }
}
