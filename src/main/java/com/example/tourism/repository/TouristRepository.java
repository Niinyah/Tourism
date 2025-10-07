package com.example.tourism.repository;

import com.example.tourism.model.Tags;
import com.example.tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        populateAttractions();
    }

    public void populateAttractions() {
        TouristAttraction touristAttraction1 = new TouristAttraction("Den lille havfrue", "Attraktion", List.of(Tags.Natur), "København");
        TouristAttraction touristAttraction2 = new TouristAttraction("Tivoli", "Forlystelsespark", List.of(Tags.Underholdning), "København");
        TouristAttraction touristAttraction3 = new TouristAttraction("Rundetårn", "Tårn", List.of(Tags.Museum), "København");
        TouristAttraction touristAttraction4 = new TouristAttraction("Noma", "Restaurant", List.of(Tags.Restaurant), "København");

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
            if (touristAttraction.getName().equals(name)) {
                return touristAttraction;
            }
        }
        return null;
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }

    public void updateTouristAttraction(TouristAttraction touristAttraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getName().equalsIgnoreCase(touristAttraction.getName())) {
                attractions.set(i, touristAttraction);
                return;
            }
        }
    }

    public void deleteTouristAttraction(String name) {
        TouristAttraction found = findAttractionsByName(name);
        if (found != null) {
            attractions.remove(found);
        }
    }


}


