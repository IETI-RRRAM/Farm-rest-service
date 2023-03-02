package edu.eci.agronomo.farm.service.land;

import edu.eci.agronomo.farm.model.land.Land;
import edu.eci.agronomo.farm.model.land.LandDto;
import edu.eci.agronomo.farm.respository.land.LandMongoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class LandServiceImplTest {

    @MockBean
    private LandMongoRepository landMongoRepository;

    @Autowired
    private LandService landService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){ mockMvc = standaloneSetup(landService).build();}

    @Test
    public void testFindByIdExistingFarm() throws Exception {
        Land land = new Land("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        when(landMongoRepository.findById("1")).thenReturn(Optional.of(land));

        Land testLand = landService.getById("1").orElseThrow();

        Assertions.assertEquals(land, testLand);

        verify(landMongoRepository, times(1)).findById("1");
    }

    @Test
    public void testFindAllFarms() throws Exception {
        Land land = new Land("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Land land1 = new Land("2", "Ada1", "Ganado1", "Cogua1", (float) 0.22321);
        Land land2 = new Land("3", "Ada2", "Ganado2", "Cogua2", (float) 0.22321);
        Land land3 = new Land("4", "Ada3", "Ganado3", "Cogua3", (float) 0.22321);

        List<Land> landList = new ArrayList<>();

        landList.add(land);
        landList.add(land1);
        landList.add(land2);
        landList.add(land3);
        when(landMongoRepository.findAll()).thenReturn(landList);

        List<Land> testLands = landService.getAll();

        Assertions.assertEquals(landList, testLands);

        verify(landMongoRepository, times(1)).findAll();
    }

    @Test
    public void testCreateFarm() throws Exception {
        Land land = new Land("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        when(landMongoRepository.save(land)).thenReturn(land);

        Land testLand = landService.create(land);

        Assertions.assertEquals(land, testLand);

        verify(landMongoRepository, times(1)).save(land);
    }

    @Test
    public void testDeleteFarm() throws Exception {
        Land land = new Land("1", "Ada", "Ganado", "Cogua", (float) 0.22321);

        landService.deleteById("1");

        verify(landMongoRepository, times(1)).deleteById("1");
    }

    @Test
    public void testUpdateFarm() throws Exception {
        Land land = new Land("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        LandDto landDto = new LandDto("1", "Ada2", "Ganado", "Cogua", (float) 0.22321);
        Land land2 = new Land("1", "Ada2", "Ganado", "Cogua", (float) 0.22321);

        when(landMongoRepository.findById("1")).thenReturn(Optional.of(land));
        when(landMongoRepository.save(land)).thenReturn(land2);
        Land saved = landService.update("1", landDto);

        Assertions.assertEquals(land2.getName(), saved.getName());

        verify(landMongoRepository, times(1)).save(land);
    }
}
