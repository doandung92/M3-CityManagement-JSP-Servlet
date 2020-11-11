package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/city")
public class CityController extends HttpServlet {
    CityService cityService = new CityService();
    CountryService countryService = new CountryService();
    RequestDispatcher dispatcher;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                addCity(request, response);
                break;
            case "save":
                saveCity(request, response);
                break;
            case "delete":
                deleteCity(request, response);
                break;
            default:
                showAllCity(request, response);
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "detail":
                showDetail(request,response);
                break;
            case "delete":
                showDeleteForm(request,response);
                break;
            default:
                showAllCity(request, response);
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        City city = cityService.getCityById(id);
        request.setAttribute("city",city);
        dispatcher = request.getRequestDispatcher("/view/city/delete.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        City city =  cityService.getCityById(id);
        request.setAttribute("city",city);
        dispatcher = request.getRequestDispatcher("/view/city/detail.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isSucceeded = cityService.delete(id);
        if (isSucceeded) {
            HttpSession session = request.getSession();
            session.setAttribute("message","Deleted the selected city");
        }
        try {
            response.sendRedirect("/city");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCity(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        double area = Double.parseDouble(request.getParameter("area"));
        int population = Integer.parseInt(request.getParameter("population"));
        double gdp = Double.parseDouble(request.getParameter("gdp"));
        String description = request.getParameter("description");
        City city = new City(id,name,country,area,population,gdp,description);
        if (!cityService.validateCity(city)) {
            request.setAttribute("city",city);
            List<String> countries = countryService.getAllCountries();
            request.setAttribute("countries",countries);
            dispatcher = request.getRequestDispatcher("/view/city/edit.jsp");
            return;
        }
        boolean isSucceeded = cityService.save(city);
        if (isSucceeded) {
            HttpSession session = request.getSession();
            session.setAttribute("message","Your edit data is saved in database");
        }
        try {
            response.sendRedirect("/city");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        City city = cityService.getCityById(id);
        request.setAttribute("city",city);
        List<String> countries = countryService.getAllCountries();
        request.setAttribute("countries",countries);
        dispatcher = request.getRequestDispatcher("/view/city/edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCity(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        double area = Double.parseDouble(request.getParameter("area"));
        int population = Integer.parseInt(request.getParameter("population"));
        double gdp = Double.parseDouble(request.getParameter("gdp"));
        String description = request.getParameter("description");
        City city = new City(name,country,area,population,gdp,description);
        if (!cityService.validateCity(city)) {
            dispatcher = request.getRequestDispatcher("/view/city/add.jsp");
            List<String> countries = countryService.getAllCountries();
            request.setAttribute("countries",countries);
            request.setAttribute("city",city);
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        boolean isSucceeded = cityService.add(city);
        if (isSucceeded){
            HttpSession session = request.getSession();
            session.setAttribute("message","Your new city is added to database");
        }
        try {
            response.sendRedirect("/city");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        dispatcher = request.getRequestDispatcher("/view/city/add.jsp");
        List<String> countries = countryService.getAllCountries();
        request.setAttribute("countries",countries);
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAllCity(HttpServletRequest request, HttpServletResponse response) {
        List<City> cities = cityService.getAllCities();
        request.setAttribute("cities", cities);
        dispatcher = request.getRequestDispatcher("/view/city/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
