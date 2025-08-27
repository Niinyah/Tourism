package com.example.tourism.service;

import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository){
        this.touristRepository = touristRepository;
    }


    public List<TouristAttraction> getAttractions() {
        return touristRepository.getAttractions();
    }

    public TouristAttraction findAttractionsByName(String name){
        return touristRepository.findAttractionsByName(name);

    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction){
        return touristRepository.addTouristAttraction(touristAttraction);
    }


}
