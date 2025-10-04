package com.example.tourism.repository;

import com.example.tourism.model.Tags;
import com.example.tourism.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CreateDbRepository {

    private JdbcTemplate jdbcTemplate;

    public CreateDbRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //createTables();
        //loadTagsIntoDB();
        //loadTouristAttractionsIntoDB();

    }

    public void createTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS touristAttractions");
        jdbcTemplate.execute("DROP TABLE IF EXISTS tags");
        jdbcTemplate.execute("DROP TABLE IF EXISTS relation");

        jdbcTemplate.execute("""
                  CREATE TABLE touristAttractions (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(150) NOT NULL,
                    description VARCHAR(150) NOT NULL,
                    city VARCHAR(150) NOT NULL
                  )
                """);

        jdbcTemplate.execute("""
                  CREATE TABLE attraction_tags (
                    attraction_id INT NOT NULL,
                    tag_name VARCHAR(150) NOT NULL,
                    PRIMARY KEY (attraction_id, tag_name),
                    FOREIGN KEY (attraction_id) REFERENCES tourist_attractions(id) ON DELETE CASCADE
                  )
                """);

//        jdbcTemplate.execute("""
//                  CREATE TABLE relation (
//                    touristAttraction_id INT NOT NULL,
//                    tag_id INT NOT NULL,
//                    PRIMARY KEY (touristAttraction_id, tag_id),
//                    FOREIGN KEY (touristAttraction_id)
//                      REFERENCES touristAttractions(id) ON DELETE CASCADE,
//                    FOREIGN KEY (tag_id)
//                      REFERENCES tags(id) ON DELETE RESTRICT
//                  )
//                """);
    }

    public void loadTagsIntoDB() {
        List<Tags> tags = Arrays.stream(Tags.values()).toList();
        for (Tags tag : tags) {
            String sql = "INSTERT INTO attraction_tags (name) VALUES (?)";
            jdbcTemplate.update(sql, tag.toString());
        }

    }

    public void loadTouristAttractionsIntoDB() {
        List<TouristAttraction> touristAttractions = new ArrayList<>(Arrays.asList(
                new TouristAttraction("Den lille havfrue", "Attraktion", List.of(Tags.Natur, Tags.Restaurant), "København"),
                new TouristAttraction("Tivoli", "Forlystelsespark", List.of(Tags.Underholdning), "København"),
                new TouristAttraction("Rundetårn", "Tårn", List.of(Tags.Museum), "København"),
                new TouristAttraction("Noma", "Restaurant", List.of(Tags.Restaurant), "København")));

        for (TouristAttraction touristAttraction : touristAttractions) {
            String sql = "INSERT INTO touristAttractions (name, description, city) VALUES (?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, touristAttraction.getName());
                ps.setString(2, touristAttraction.getDescription());
                ps.setString(3, touristAttraction.getCity());
                return ps;
            }, keyHolder);

            int touristAttractionId = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
            touristAttraction.setId(touristAttractionId);


        }
    }
}
