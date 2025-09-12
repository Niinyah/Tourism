package com.example.tourism;
import com.example.tourism.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
class TourismApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;


}
