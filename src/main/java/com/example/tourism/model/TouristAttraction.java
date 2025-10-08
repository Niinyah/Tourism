package com.example.tourism.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private List<Tags> tags = new ArrayList<>();
    private String city;
    private int id;


    public TouristAttraction(String name, String description, List<Tags> tags, String city, int id){
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.city = city;
        this.id = id;

    }

    public TouristAttraction(){}

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void addTag(Tags tag){
        tags.add(tag);
    }

    public void removeTag(Tags tag){
        tags.remove(tag);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
