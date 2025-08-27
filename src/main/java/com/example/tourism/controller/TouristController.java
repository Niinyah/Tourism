package com.example.tourism.controller;

import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("attractions")
@Controller
public class TouristController {
    private final TouristService service;


    public TouristController(TouristService touristService) {
        this.service = touristService;
    }

    @GetMapping()
    public ResponseEntity<List<TouristAttraction>> getAttractions() {
        List<TouristAttraction> attractions = service.getAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);

    }
    @GetMapping("/name")
    public ResponseEntity<TouristAttraction>findAttractionsByName(@PathVariable String name) {
        TouristAttraction touristAttraction = service.findAttractionsByName(name);
        if(touristAttraction != null){
            return new ResponseEntity<>(touristAttraction, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/add")
    public ResponseEntity<TouristAttraction>addTouristAttraction(@RequestBody TouristAttraction touristAttraction){
        TouristAttraction touristAttraction1 = service.addTouristAttraction(touristAttraction);
        return new ResponseEntity<>(touristAttraction1, HttpStatus.CREATED);
    }


}
