package com.microservicedemo.locationservice.service;

import com.microservicedemo.locationservice.entity.Location;
import com.microservicedemo.locationservice.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Optional<Location> findById(long id) {
        return locationRepository.findById(id);
    }

    public void delete(long id) {
        locationRepository.deleteById(id);
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }
}
