package com.himj.placesearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PlaceSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaceSearchServiceApplication.class, args);
    }

}
