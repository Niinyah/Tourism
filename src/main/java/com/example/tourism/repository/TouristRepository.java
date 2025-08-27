package com.example.tourism.repository;

import com.example.tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private List<TouristRepository> attractions = new ArrayList<>();



    public TouristRepository(List<TouristRepository> attractions){
        this.attractions = attractions;
    }
    public void setAttractions(List<TouristRepository> attractions) {
        this.attractions = attractions;
    }

    public void populateAttractions() {
        TouristAttraction touristAttraction1 = new TouristAttraction("Den lille havfrue","Attraction");
        TouristAttraction touristAttraction2 = new TouristAttraction("Tivoli", "Amusementpark");
        TouristAttraction touristAttraction3 = new TouristAttraction("Det Runde Tårn", "Tårn");
        TouristAttraction touristAttraction4 = new TouristAttraction("Noma", "Restaurant");
        
    }
}

}
