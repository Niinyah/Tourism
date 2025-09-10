package com.example.tourism.controller;

import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAttractions() {
    }

    @Test
    void addTouristAttraction() throws Exception{
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("attraction", instanceOf(TouristAttraction.class)))
                .andExpect(view().name("addTouristAttraction"));
    }


//    @Test
//    void ArgumentCaptor() throws Exception {
//        TouristAttraction touristAttraction = new TouristAttraction("Tivoli","Forlystelsespark", List.of(Tags.ENTERTAINMENT),"Copenhagen");
//        when(touristService.addTouristAttraction(any (TouristAttraction.class))).thenReturn(touristAttraction);
//
//        mockMvc.perform(get("/add"))
//                //        .param("attraction", "Tivoli"))
//                //.andExpect(status().is3xxRedirection())
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("attraction", touristAttraction))
//                .andExpect(view().name("addTouristAttraction"));
//
//        ArgumentCaptor<TouristAttraction> captor = ArgumentCaptor.forClass(TouristAttraction.class);
//        verify(touristService).addTouristAttraction(captor.capture());
//
//        TouristAttraction captured = captor.getValue();
//        assertEquals("Tivoli", captured.getName());
//        assertEquals("Forlystelsespark", captured.getDescription());
//        assertEquals(List.of(Tags.ENTERTAINMENT), captured.getTags());
//        assertEquals("Copenhagen", captured.getCity());
//        assertNotNull(captured.getClass());
//    }
}