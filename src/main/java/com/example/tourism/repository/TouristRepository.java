package com.example.tourism.repository;

import com.example.tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractions = new ArrayList<>();


    public TouristRepository(List<TouristAttraction> attractions){
        this.attractions = attractions;
        populateAttractions();
    }
    public void setAttractions(List<TouristAttraction> attractions) {
        this.attractions = attractions;
    }


    public void populateAttractions() {
        TouristAttraction touristAttraction1 = new TouristAttraction("Den lille havfrue","Attraction");
        TouristAttraction touristAttraction2 = new TouristAttraction("Tivoli", "Amusementpark");
        TouristAttraction touristAttraction3 = new TouristAttraction("Det Runde Tårn", "Tårn");
        TouristAttraction touristAttraction4 = new TouristAttraction("Noma", "Restaurant");

        attractions.add(touristAttraction1);
        attractions.add(touristAttraction2);
        attractions.add(touristAttraction3);
        attractions.add(touristAttraction4);
    }

    public List<TouristAttraction> getAttractions() {
        return attractions;
    }

    public TouristAttraction findAttractionsByName(String name) {
        for (TouristAttraction touristAttraction : attractions) {
            if (touristAttraction.equals(name)) {
                return touristAttraction;
            }
        }
        return null;
    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction){
        TouristAttraction addAttraction = touristAttraction;
        attractions.add(addAttraction);
        return addAttraction;
    }
}


