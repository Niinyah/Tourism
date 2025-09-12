package com.example.tourism.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private List<Tags> tags;
    private String city;

    public TouristAttraction(String name, String description, List<Tags> tags, String city){
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.city = city;

    }

    public TouristAttraction(){}

    public List<Tags> getTags() {
        return tags;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
