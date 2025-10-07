package com.example.tourism.repository;

import com.example.tourism.model.Tags;
import com.example.tourism.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class TouristRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private final JdbcTemplate jdbcTemplate;


    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<TouristAttraction> getAttractions() {
        final String sql = """
                    SELECT
                      ta.id          AS ta_id,
                      ta.name        AS ta_name,
                      ta.description AS ta_description,
                      ta.city        AS ta_city,
                      at.tag_name    AS tag_name
                    FROM touristAttractions ta
                    LEFT JOIN attraction_tags at ON at.attraction_id = ta.id
                    ORDER BY ta.id
                """;

        return jdbcTemplate.query(sql, rs -> {
            Map<Integer, TouristAttraction> byId = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt("ta_id");
                TouristAttraction ta = byId.get(id);
                if (ta == null) {
                    ta = new TouristAttraction();
                    ta.setId(id);
                    ta.setName(rs.getString("ta_name"));
                    ta.setDescription(rs.getString("ta_description"));
                    ta.setCity(rs.getString("ta_city"));
                    byId.put(id, ta);
                }

                String tagName = rs.getString("tag_name");
                if (tagName != null) {
                    ta.addTag(Tags.valueOf(tagName));

                }
            }
            return new ArrayList<>(byId.values());
        });
    }

    public TouristAttraction findAttractionsByName(String name) {
        List<TouristAttraction> attractions = getAttractions();
        for (TouristAttraction touristAttraction : attractions) {
            if (touristAttraction.getName().equals(name)) {
                return touristAttraction;
            }
        }
        return null;
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        String sql = "INSERT INTO touristAttractions (name, description, city) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, touristAttraction.getName());
            ps.setString(2, touristAttraction.getDescription());
            ps.setString(3, touristAttraction.getCity());
            return ps;
        }, keyHolder);
        int attraction_id = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
        List<Tags> tags = touristAttraction.getTags();
        for (Tags t : tags) {
            String sqlTags = "INSERT INTO attraction_tags (attraction_id, tag_name) VALUES (?,?) ";

            jdbcTemplate.update(sqlTags,
                    attraction_id,
                    t.toString());

        }
    }

    public void updateTouristAttraction(TouristAttraction touristAttraction) {
        String sql = "UPDATE touristAttractions SET name = ?, description = ?, city = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                touristAttraction.getName(),
                touristAttraction.getDescription(),
                touristAttraction.getCity(),
                touristAttraction.getId());

        List<TouristAttraction> touristAttractions = getAttractions();
        for (TouristAttraction ta : touristAttractions) {
            if (ta.getId() == touristAttraction.getId()) {
                List<Tags> oldTags = ta.getTags();
                List<Tags> newTags = ta.getTags();
                if (oldTags.size() < newTags.size()) {
                    for (Tags t : oldTags) {
                        for (Tags n : newTags)
                        if (t.equals(n))
                        String sql_tags = "INSERT INTO attraction_tags (attraction_id, tag_name) VALUES (?, ?)";
                        jdbcTemplate.update(sql_tags, )
                    }
                }
            }
        }
    }

    public void deleteTouristAttraction(String name) {
        TouristAttraction ta = findAttractionsByName(name);
        String SQL = "DELETE FROM touristAttractions WHERE id = ?";
        jdbcTemplate.update(SQL, ta.getId());
    }

//    public void createTables() {
////        jdbcTemplate.execute("DROP TABLE IF EXISTS touristAttractions");
////        jdbcTemplate.execute("DROP TABLE IF EXISTS attraction_tags");
//
//        jdbcTemplate.execute("""
//                  CREATE TABLE touristAttractions (
//                    id INT AUTO_INCREMENT PRIMARY KEY,
//                    name VARCHAR(150) NOT NULL,
//                    description VARCHAR(150) NOT NULL,
//                    city VARCHAR(150) NOT NULL
//                  )
//                """);
//
//        jdbcTemplate.execute("""
//                  CREATE TABLE attraction_tags (
//                    attraction_id INT NOT NULL,
//                    tag_name VARCHAR(150) NOT NULL,
//                    PRIMARY KEY (attraction_id, tag_name),
//                    FOREIGN KEY (attraction_id) REFERENCES tourist_attractions(id) ON DELETE CASCADE
//                  )
//                """);
//    }


}


