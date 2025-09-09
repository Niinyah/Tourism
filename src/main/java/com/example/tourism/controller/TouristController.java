package com.example.tourism.controller;

import com.example.tourism.model.Tags;
import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("attractions")
@Controller
public class TouristController {
    private final TouristService service;


    public TouristController(TouristService touristService) {
        this.service = touristService;
    }

    @GetMapping()
    public String getAttractions(Model model) {
        List<TouristAttraction> attractions = service.getAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";

    }

    @GetMapping("/{name}/tags")
    public String getTags( @PathVariable String name, Model model){
        TouristAttraction touristAttraction = service.findAttractionsByName(name);
        model.addAttribute("attraction", touristAttraction);
        return "tags";

    }

    @GetMapping("{name}")
    public ResponseEntity<TouristAttraction> findAttractionsByName(@PathVariable String name) {
        TouristAttraction touristAttraction = service.findAttractionsByName(name);
        if (touristAttraction != null) {
            return new ResponseEntity<>(touristAttraction, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/add")
    public String addTouristAttraction(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction();
        model.addAttribute("attraction", touristAttraction);
        List<Tags> tags = Arrays.stream(Tags.values()).toList();
        model.addAttribute("tags",tags);
        return "addTouristAttraction";
    }

    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> updateTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        TouristAttraction touristAttraction1 = service.updateTouristAttraction(touristAttraction.getName(), touristAttraction.getDescription());

        if (touristAttraction1 == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(touristAttraction1, HttpStatus.CREATED);
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteTouristAttraction(@PathVariable String name) {
        TouristAttraction touristAttraction1 = service.deleteTouristAttraction(name);

        if (touristAttraction1 == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(touristAttraction1, HttpStatus.OK);

    }
}
