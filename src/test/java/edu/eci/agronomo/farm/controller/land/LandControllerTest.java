package edu.eci.agronomo.farm.controller.land;

import edu.eci.agronomo.farm.exception.LandNotFoundException;
import edu.eci.agronomo.farm.model.land.Land;
import edu.eci.agronomo.farm.model.land.LandDto;
import edu.eci.agronomo.farm.service.land.LandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class LandControllerTest {

    final String BASE_URL = "/v1/land/";
    @MockBean
    private LandService landService;
    @Autowired
    private LandController landController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){ mockMvc = standaloneSetup(landController).build();}

    @Test
    public void testFindByIdExistingLand() throws Exception {
        Land land = new Land("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        when(landService.getById("1")).thenReturn(Optional.of(land));

        mockMvc.perform(get(BASE_URL + "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ada")))
                .andExpect(jsonPath("$.subPurpose", is("Ganado")))
                .andExpect(jsonPath("$.location", is("Cogua")));

        verify(landService, times(1)).getById("1");
    }

    @Test
    public void testFindByAllLands() throws Exception {
        Land land = new Land("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Land land1 = new Land("2", "Ada1", "Ganado1", "Cogua1", (float) 0.22321);
        Land land2 = new Land("3", "Ada2", "Ganado2", "Cogua2", (float) 0.22321);
        Land land3 = new Land("4", "Ada3", "Ganado3", "Cogua3", (float) 0.22321);

        List<Land> landList = new ArrayList<>();

        landList.add(land);
        landList.add(land1);
        landList.add(land2);
        landList.add(land3);
        when(landService.getAll()).thenReturn(landList);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[2].name", is("Ada2")))
                .andExpect(jsonPath("$.[2].subPurpose", is("Ganado2")))
                .andExpect(jsonPath("$.[2].location", is("Cogua2")))
                .andExpect(jsonPath("$.[1].name", is("Ada1")))
                .andExpect(jsonPath("$.[1].subPurpose", is("Ganado1")))
                .andExpect(jsonPath("$.[1].location", is("Cogua1")));

        verify(landService, times(1)).getAll();
    }

    @Test
    public void testFindByIdNotExistingLand() throws Exception {
        String id = "511";
        when(landService.getById(id)).thenReturn(Optional.empty());


        mockMvc.perform(get(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LandNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"land with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(landService, times(1)).getById(id);

    }


    @Test
    public void testSaveNewLand() throws Exception {
        LandDto landDto = new LandDto("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Land land = new Land(landDto);

        when(landService.create(any())).thenReturn(land);

        String json = "{\"id\":\"null\",\"name\":\"Ada\",\"lastName\":\"Lovelace\"}";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(landService, times(1)).create(any());
    }

    @Test
    public void testUpdateExistingLand() throws Exception {
        LandDto landDto = new LandDto("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        LandDto landDtoUpdated = new LandDto("Ada", "Ada", "Ganado", "Cogua", (float) 0.0124);
        Land land = new Land(landDto);
        when(landService.getById("1")).thenReturn(Optional.of(land));
        when(landService.update("1",landDtoUpdated)).thenReturn(land);

        String json = "{\"id\":\"1\",\"farmId\":\"Ada\",\"name\":\"Ada\",\"subPurpose\":\"Ganado\",\"location\":\"Cogua\",\"area\":\"0.0124\"}";
        mockMvc.perform(put(BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(landService, times(1)).create(land);
    }

    @Test
    public void testUpdateNotExistingLand() throws Exception {
        String id = "1";
        when(landService.getById(id)).thenReturn(Optional.empty());
        String json = "{\"id\":\"1\",\"ownerId\":\"Ada\",\"name\":\"Ada\",\"purpose\":\"Ganado\",\"location\":\"Cogua\",\"area\":\"0.0124\"}";
        mockMvc.perform(put(BASE_URL + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LandNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"land with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(landService, times(0)).create(any());
    }

    @Test
    public void testDeleteExistingLand() throws Exception {
        LandDto landDto = new LandDto("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Land land = new Land(landDto);
        when(landService.getById("1")).thenReturn(Optional.of(land));

        String json = "{\"id\":\"1\",\"ownerId\":\"Ada\",\"name\":\"Ada\",\"purpose\":\"Ganado\",\"location\":\"Cogua\",\"area\":\"0.0124\"}";
        mockMvc.perform(delete(BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(landService, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteNotExistingLand() throws Exception {
        String id = "1";
        when(landService.getById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LandNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"land with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(landService, times(0)).deleteById(id);
    }
}
