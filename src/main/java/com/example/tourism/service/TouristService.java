package com.example.tourism.service;

import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }


    public List<TouristAttraction> getAttractions() {
        return touristRepository.getAttractions();
    }

    public TouristAttraction findAttractionsByName(String name) {
        return touristRepository.findAttractionsByName(name);

    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristRepository.addTouristAttraction(touristAttraction);
    }

    public void updateTouristAttraction(TouristAttraction touristAttraction) {
        touristRepository.updateTouristAttraction(touristAttraction);
    }

    public void deleteTouristAttraction(String name) {
        touristRepository.deleteTouristAttraction(name);
    }


}
