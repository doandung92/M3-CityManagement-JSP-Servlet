package com.codegym.DAO;

import com.codegym.DatabaseConnector.DatabaseConnector;
import com.codegym.model.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private final String selectAllCountry = "select * from country";
    public List<String> getAllCountries() {
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {
            List<String> list = new ArrayList<>();
            ResultSet rs = statement.executeQuery(selectAllCountry);
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
