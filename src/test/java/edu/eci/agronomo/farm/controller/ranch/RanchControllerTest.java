package edu.eci.agronomo.farm.controller.ranch;

import edu.eci.agronomo.farm.exception.RanchNotFoundException;
import edu.eci.agronomo.farm.model.ranch.Ranch;
import edu.eci.agronomo.farm.model.ranch.RanchDto;
import edu.eci.agronomo.farm.service.ranch.RanchService;
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
public class RanchControllerTest {
    final String BASE_URL = "/v1/ranch/";
    @MockBean
    private RanchService ranchService;
    @Autowired
    private RanchController ranchController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){ mockMvc = standaloneSetup(ranchController).build();}

    @Test
    public void testFindByIdExistingRanch() throws Exception {
        Ranch ranch = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        when(ranchService.getById("1")).thenReturn(Optional.of(ranch));

        mockMvc.perform(get(BASE_URL + "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ada")))
                .andExpect(jsonPath("$.subPurpose", is("Ganado")))
                .andExpect(jsonPath("$.location", is("Cogua")));

        verify(ranchService, times(1)).getById("1");
    }

    @Test
    public void testFindByAllRanches() throws Exception {
        Ranch ranch = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Ranch ranch2 = new Ranch("2", "Ada2", "Ganado2", "Cogua2", (float) 0.22321);
        Ranch ranch3 = new Ranch("3", "Ada3", "Ganado3", "Cogua3", (float) 0.22321);
        Ranch ranch4 = new Ranch("4", "Ada4", "Ganado4", "Cogua4", (float) 0.22321);

        List<Ranch> ranchList = new ArrayList<>();

        ranchList.add(ranch);
        ranchList.add(ranch2);
        ranchList.add(ranch3);
        ranchList.add(ranch4);

        when(ranchService.getAll()).thenReturn(ranchList);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[2].name", is("Ada3")))
                .andExpect(jsonPath("$.[2].subPurpose", is("Ganado3")))
                .andExpect(jsonPath("$.[2].location", is("Cogua3")))
                .andExpect(jsonPath("$.[1].name", is("Ada2")))
                .andExpect(jsonPath("$.[1].subPurpose", is("Ganado2")))
                .andExpect(jsonPath("$.[1].location", is("Cogua2")));

        verify(ranchService, times(1)).getAll();
    }

    @Test
    public void testFindByIdNotExistingRanches() throws Exception {
        String id = "511";
        when(ranchService.getById(id)).thenReturn(Optional.empty());


        mockMvc.perform(get(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RanchNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"ranch with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(ranchService, times(1)).getById(id);

    }


    @Test
    public void testSaveNewURanch() throws Exception {
        RanchDto ranchDto = new RanchDto("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Ranch ranch = new Ranch();
        ranch.update(ranchDto);

        when(ranchService.create(any())).thenReturn(ranch);

        String json = "{\"id\":\"null\",\"name\":\"Ada\",\"lastName\":\"Lovelace\"}";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(ranchService, times(1)).create(any());
    }

    @Test
    public void testUpdateExistingRanch() throws Exception {
        RanchDto ranchDto = new RanchDto("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        RanchDto ranchDtoUpdated = new RanchDto("Ada", "Ada", "Ganado", "Cogua", (float) 0.0124);
        Ranch ranch = new Ranch();
        ranch.update(ranchDto);
        when(ranchService.getById("1")).thenReturn(Optional.of(ranch));
        when(ranchService.update("1",ranchDtoUpdated)).thenReturn(ranch);

        String json = "{\"id\":\"1\",\"landId\":\"Ada\",\"name\":\"Ada\",\"subPurpose\":\"Ganado\",\"location\":\"Cogua\",\"area\":\"0.0124\"}";
        mockMvc.perform(put(BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(ranchService, times(1)).create(ranch);
    }

    @Test
    public void testUpdateNotExistingRanch() throws Exception {
        String id = "1";
        when(ranchService.getById(id)).thenReturn(Optional.empty());
        String json = "{\"id\":\"1\",\"ownerId\":\"Ada\",\"name\":\"Ada\",\"purpose\":\"Ganado\",\"location\":\"Cogua\",\"area\":\"0.0124\"}";
        mockMvc.perform(put(BASE_URL + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RanchNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"ranch with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(ranchService, times(0)).create(any());
    }

    @Test
    public void testDeleteExistingRanch() throws Exception {
        RanchDto ranchDto = new RanchDto("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Ranch ranch = new Ranch();
        ranch.update(ranchDto);
        when(ranchService.getById("1")).thenReturn(Optional.of(ranch));

        String json = "{\"id\":\"1\",\"ownerId\":\"Ada\",\"name\":\"Ada\",\"purpose\":\"Ganado\",\"location\":\"Cogua\",\"area\":\"0.0124\"}";
        mockMvc.perform(delete(BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(ranchService, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteNotExistingRanch() throws Exception {
        String id = "1";
        when(ranchService.getById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RanchNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"ranch with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(ranchService, times(0)).deleteById(id);
    }

}
