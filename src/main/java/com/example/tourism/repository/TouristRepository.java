package com.example.tourism.repository;

import com.example.tourism.model.Tags;
import com.example.tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository(){
        populateAttractions();
    }

    public void populateAttractions() {
        TouristAttraction touristAttraction1 = new TouristAttraction("Den lille havfrue","Attraction",List.of(Tags.NATURE), "Copenhagen");
        TouristAttraction touristAttraction2 = new TouristAttraction("Tivoli", "Amusementpark", List.of(Tags.ENTERTAINMENT), "Copenhagen");
        TouristAttraction touristAttraction3 = new TouristAttraction("Rundetårn", "Tårn", List.of(Tags.MUSEUM), "Copenhagen");
        TouristAttraction touristAttraction4 = new TouristAttraction("Noma", "Restaurant", List.of(Tags.RESTAURANT), "Copenhagen");

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

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction){
        TouristAttraction addAttraction = touristAttraction;
        attractions.add(addAttraction);
        return addAttraction;
    }

    public TouristAttraction updateTouristAttraction(String name, String newDescription) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setDescription(newDescription);
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction deleteTouristAttraction(String name) {
        TouristAttraction found = findAttractionsByName(name);
        if (found != null) {
            attractions.remove(found);
        }
        return found;
    }



}


