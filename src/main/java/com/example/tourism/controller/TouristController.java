package com.example.tourism.controller;

import com.example.tourism.model.Tags;
import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
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
        //noinspection SpringMVCViewInspection
        return "tags";

    }


    @GetMapping("/add")
    public String addTouristAttraction(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction();
        model.addAttribute("attraction", touristAttraction);
        List<Tags> tags = Arrays.stream(Tags.values()).toList();
        model.addAttribute("tags",tags);
        return "addTouristAttraction";
    }

    @PostMapping("/save")
    public String saveTouristAttraction(@ModelAttribute TouristAttraction touristAttraction){
        service.addTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editTouristAttraction(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = service.findAttractionsByName(name);
        model.addAttribute("attraction", touristAttraction);
        List<Tags> tags = Arrays.stream(Tags.values()).toList();
        model.addAttribute("tags",tags);
        return "editTouristAttraction";

    }

    @PostMapping("/update")
    public String updateTouristAttraction(@ModelAttribute TouristAttraction touristAttraction){
        service.updateTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/{name}/delete")
    public String deleteTouristAttraction(@PathVariable String name) {
        service.deleteTouristAttraction(name);
        return "redirect:/attractions";
    }
}
