/**
 * We should be returning the data in JSON format
 * Database schema: 'springbootdb'
 */
package com.rijai.LocationApi.controller;

import com.rijai.LocationApi.model.Country;
import com.rijai.LocationApi.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// this is where the front-end (AngularJS) is using
// hence, for the front-end to use this backend API,
// we will be using port:4200 where the server will be accessing this
@CrossOrigin(origins = "http://localhost:4200")     // address of the Node.js server, being utilized by AngularJS
/*
    Node.js (utitlized by AngularJS) communicates bidirectional to Tomcat Server (utilized by Spring Boot)
 */

@RestController // Used by the backend application if we want it as an API
public class MyController {

    /**
     * All data should be represented as a JSON format
     * Note: the view will be created using AngularJS
     */

    @Autowired
    private ICountryService countryService;

    @RequestMapping("/api/countries")
    public List<Country> findCountries() {
        /**
         * @note returns a List of countries from the database using JPA
         */
        return countryService.findAll();
    }

    @RequestMapping("/api/show-country/{id}")
    public Country showCountry(@PathVariable long id) {
        /**
         * @note returns a country by using id
         */
        return countryService.getCountry(id);
    }

    @RequestMapping(value = "/api/add-country", method = RequestMethod.POST)
    public Country addCountrySubmit(@RequestBody Country country) {
        /**
         * @note to create a POST request to add (insert) country to database
         */
        return countryService.addCountry(country);
    }

    @RequestMapping(value = "/api/update-country/{id}", method = RequestMethod.PUT)
    public Country updateCountry(@RequestBody Country country) {
        /**
         * @note to create a PUT request to update country from database
         */
        return countryService.updateCountry(country);
    }

}
