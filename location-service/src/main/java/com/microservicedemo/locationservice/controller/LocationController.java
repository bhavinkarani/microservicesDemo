package com.microservicedemo.locationservice.controller;

import com.microservicedemo.locationservice.entity.Location;
import com.microservicedemo.locationservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("")
    public List<Location> getAllLocations(){
        return locationService.findAll();
    }

    @GetMapping(params = "id")
    public Location getLocationById(@RequestParam("id")long id ){
        if(locationService.findById(id).isPresent()){
            return locationService.findById(id).get();
        }
        return null;
    }

    @DeleteMapping("")
    public ResponseEntity deleteLocationById(@RequestParam("id")long id ){
        locationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addLocation(@RequestBody Location location){
        return new ResponseEntity(locationService.save(location), HttpStatus.OK);
    }
}
