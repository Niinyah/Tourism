package com.example.tourism;

import com.example.tourism.model.Tags;
import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
class TouristRepositoryTest {

    @Autowired
    private TouristRepository repo;

    @Test
    void readAll() {
        List<TouristAttraction> all = repo.getAttractions();

        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.getFirst().getName()).isEqualTo("Tivoli");
    }


    @Test
    void insertAndReadBack() {
        repo.addTouristAttraction(new TouristAttraction("Bakken",
                "sjov",
                Arrays.stream(Tags.values()).toList(),
                "københavn", 3));
        var bakken = repo.findAttractionsByName("Bakken");
        assertThat(bakken).isNotNull();
        assertThat(bakken.getName()).isEqualTo("Bakken");
    }
}