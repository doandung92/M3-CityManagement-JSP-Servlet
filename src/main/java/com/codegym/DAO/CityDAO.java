package com.codegym.DAO;

import com.codegym.DatabaseConnector.DatabaseConnector;
import com.codegym.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    private final String insertIntoCity = "insert into city (name,country,area,population,gdp,description) values (?,?,?,?,?,?)";
    private final String selectAllCity = "select * from city";
    private final String selectCityById = "select * from city where id = ?";
    private final String updateCity = "update city set name = ?, country = ?, area = ?, population = ?, description = ?, gdp = ? where id = ?";
    private final String deleteCity = "delete from city where id = ?";
    public List<City> getAllCities() {
        try( Connection connection = DatabaseConnector.getConnection();
                Statement statement = connection.createStatement() ){
            List<City> list = new ArrayList<>();
            ResultSet rs = statement.executeQuery(selectAllCity);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                double area = rs.getDouble("area");
                int population = rs.getInt("population");
                double gbp = rs.getDouble("gdp");
                String description = rs.getString("description");
                City city = new City(id,name,country,area,population,gbp,description);
                list.add(city);
            }
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public City getCityById(int id) {
        try( Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectCityById) ){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                String name = rs.getString("name");
                String country = rs.getString("country");
                double area = rs.getDouble("area");
                int population = rs.getInt("population");
                double gbp = rs.getDouble("gdp");
                String description = rs.getString("description");
                return new City(id,name,country,area,population,gbp,description);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean add(City city) {
        try( Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertIntoCity) ){
            preparedStatement.setString(1,city.getName());
            preparedStatement.setString(2,city.getCountry());
            preparedStatement.setDouble(3,city.getArea());
            preparedStatement.setInt(4,city.getPopulation());
            preparedStatement.setDouble(5,city.getGdp());
            preparedStatement.setString(6,city.getDescription());
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean save(City city) {
        try( Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCity) ){
            preparedStatement.setString(1,city.getName());
            preparedStatement.setString(2,city.getCountry());
            preparedStatement.setDouble(3,city.getArea());
            preparedStatement.setInt(4,city.getPopulation());
            preparedStatement.setString(5,city.getDescription());
            preparedStatement.setDouble(6,city.getGdp());
            preparedStatement.setInt(7,city.getId());
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(int id) {
        try( Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteCity) ){
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
