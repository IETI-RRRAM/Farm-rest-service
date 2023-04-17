package edu.eci.agronomo.farm.controller.farm;

import edu.eci.agronomo.farm.controller.farm.FarmController;
import edu.eci.agronomo.farm.exception.FarmNotFoundException;
import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.farm.FarmDto;
import edu.eci.agronomo.farm.service.farm.FarmService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class FarmControllerTest {

    final String BASE_URL = "/v1/farm/";
    @MockBean
    private FarmService farmService;
    @Autowired
    private FarmController farmController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){ mockMvc = standaloneSetup(farmController).build();}

    @Test
    public void testFindByIdExistingFarm() throws Exception {
        Farm farm = new Farm("1", "Ada", "Ganado", "Cogua", "a" ,(float) 0.22321);
        when(farmService.getById("1")).thenReturn(Optional.of(farm));

        mockMvc.perform(get(BASE_URL + "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ada")))
                .andExpect(jsonPath("$.purpose", is("Ganado")))
                .andExpect(jsonPath("$.location", is("Cogua")));

        verify(farmService, times(1)).getById("1");
    }

    @Test
    public void testFindByAllFarms() throws Exception {
        Farm farm = new Farm("1", "Ada", "Ganado", "Cogua", "a" ,(float) 0.22321);
        Farm farm2 = new Farm("2", "Ada2", "Ganado2", "Cogua2", "a" ,(float) 0.22321);
        Farm farm3 = new Farm("3", "Ada3", "Ganado3", "Cogua3", "a" ,(float) 0.22321);
        Farm farm4 = new Farm("4", "Ada4", "Ganado4", "Cogua4", "a" ,(float) 0.22321);

        List<Farm> farmList = new ArrayList<>();

        farmList.add(farm);
        farmList.add(farm2);
        farmList.add(farm3);
        farmList.add(farm4);

        when(farmService.getAll()).thenReturn(farmList);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[2].name", is("Ada3")))
                .andExpect(jsonPath("$.[2].purpose", is("Ganado3")))
                .andExpect(jsonPath("$.[2].location", is("Cogua3")))
                .andExpect(jsonPath("$.[1].name", is("Ada2")))
                .andExpect(jsonPath("$.[1].purpose", is("Ganado2")))
                .andExpect(jsonPath("$.[1].location", is("Cogua2")));

        verify(farmService, times(1)).getAll();
    }

    @Test
    public void testFindByIdNotExistingFarm() throws Exception {
        String id = "511";
        when(farmService.getById(id)).thenReturn(Optional.empty());


        mockMvc.perform(get(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FarmNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"farm with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(farmService, times(1)).getById(id);

    }


    @Test
    public void testSaveNewFarm() throws Exception {
        FarmDto userDto = new FarmDto("1", "Ada", "Ganado", "Cogua", "a" ,(float) 0.22321);
        Farm farm = new Farm(userDto);

        when(farmService.create(any())).thenReturn(farm);

        String json = "{\"id\":\"null\",\"name\":\"Ada\",\"lastName\":\"Lovelace\"}";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(farmService, times(1)).create(any());
    }

    @Test
    public void testUpdateExistingFarm() throws Exception {
        FarmDto farmDto = new FarmDto("1", "Ada", "Ganado", "Cogua","a" ,(float) 0.22321);
        FarmDto farmDtoUpdated = new FarmDto("Ada", "Ada", "Ganado", "Cogua", "a" ,(float) 0.0124);
        Farm farm = new Farm(farmDto);
        Farm farmUpdated = new Farm(farmDtoUpdated);
        when(farmService.getById("1")).thenReturn(Optional.of(farm));
        when(farmService.update("1", farmDtoUpdated)).thenReturn(farmUpdated);

        String json = "{\"id\":\"1\",\"ownerId\":\"Ada\",\"name\":\"Ada\",\"purpose\":\"Ganado\",\"location\":\"Cogua\", \"imageUrl\":\"a\", \"area\":\"0.0124\"}";
        mockMvc.perform(put(BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(farmService, times(1)).create(farm);
    }

    @Test
    public void testUpdateNotExistingFarm() throws Exception {
        String id = "1";
        when(farmService.getById(id)).thenReturn(Optional.empty());
        String json = "{\"id\":\"1\",\"ownerId\":\"Ada\",\"name\":\"Ada\",\"purpose\":\"Ganado\",\"location\":\"Cogua\", \"imageUrl\":\"a\", \"area\":\"0.0124\"}";
        mockMvc.perform(put(BASE_URL + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FarmNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"farm with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(farmService, times(0)).create(any());
    }

    @Test
    public void testDeleteExistingFarm() throws Exception {
        FarmDto farmDto = new FarmDto("1", "Ada", "Ganado", "Cogua", "a" ,(float) 0.22321);
        Farm farm = new Farm(farmDto);
        when(farmService.getById("1")).thenReturn(Optional.of(farm));

        String json = "{\"id\":\"1\",\"ownerId\":\"Ada\",\"name\":\"Ada\",\"purpose\":\"Ganado\",\"location\":\"Cogua\", \"imageUrl\":\"a\", \"area\":\"0.0124\"}";
        mockMvc.perform(delete(BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(farmService, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteNotExistingFarm() throws Exception {
        String id = "1";
        when(farmService.getById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FarmNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"farm with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(farmService, times(0)).deleteById(id);
    }

}
