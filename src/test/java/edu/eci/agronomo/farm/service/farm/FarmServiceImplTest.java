package edu.eci.agronomo.farm.service.farm;

import edu.eci.agronomo.farm.model.farm.Farm;
import edu.eci.agronomo.farm.model.farm.FarmDto;
import edu.eci.agronomo.farm.respository.farm.FarmMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class FarmServiceImplTest {

    @MockBean
    private FarmMongoRepository farmMongoRepository;
    @Autowired
    private FarmService farmService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){ mockMvc = standaloneSetup(farmService).build();}

    @Test
    public void testFindByIdExistingFarm() throws Exception {
        Farm farm = new Farm("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        when(farmMongoRepository.findById("1")).thenReturn(Optional.of(farm));

        Farm testFarm = farmService.getById("1").orElseThrow();

        Assertions.assertEquals(farm, testFarm);

        verify(farmMongoRepository, times(1)).findById("1");
    }

    @Test
    public void testFindAllFarms() throws Exception {
        Farm farm = new Farm("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Farm farm2 = new Farm("2", "Ada2", "Ganado2", "Cogua2", (float) 0.22321);
        Farm farm3 = new Farm("3", "Ada3", "Ganado3", "Cogua3", (float) 0.22321);
        Farm farm4 = new Farm("4", "Ada4", "Ganado4", "Cogua4", (float) 0.22321);

        List<Farm> farmList = new ArrayList<>();

        farmList.add(farm);
        farmList.add(farm2);
        farmList.add(farm3);
        farmList.add(farm4);
        when(farmMongoRepository.findAll()).thenReturn(farmList);

        List<Farm> testFarms = farmService.getAll();

        Assertions.assertEquals(farmList, testFarms);

        verify(farmMongoRepository, times(1)).findAll();
    }

    @Test
    public void testCreateFarm() throws Exception {
        Farm farm = new Farm("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        when(farmMongoRepository.save(farm)).thenReturn(farm);

        Farm testFarm = farmService.create(farm);

        Assertions.assertEquals(farm, testFarm);

        verify(farmMongoRepository, times(1)).save(farm);
    }

    @Test
    public void testDeleteFarm() throws Exception {
        Farm farm = new Farm("1", "Ada", "Ganado", "Cogua", (float) 0.22321);

        farmService.deleteById("1");

        verify(farmMongoRepository, times(1)).deleteById("1");
    }

    @Test
    public void testUpdateFarm() throws Exception {
        Farm farm = new Farm("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        FarmDto farmDto = new FarmDto("1", "Ada2", "Ganado", "Cogua", (float) 0.22321);
        Farm farm2 = new Farm("1", "Ada2", "Ganado", "Cogua", (float) 0.22321);

        when(farmMongoRepository.findById("1")).thenReturn(Optional.of(farm));
        when(farmMongoRepository.save(farm)).thenReturn(farm2);
        Farm saved = farmService.update("1", farmDto);

        Assertions.assertEquals(farm2.getName(), saved.getName());

        verify(farmMongoRepository, times(1)).save(farm);
    }
}
