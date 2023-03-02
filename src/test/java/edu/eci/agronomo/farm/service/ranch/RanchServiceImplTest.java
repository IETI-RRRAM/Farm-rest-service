package edu.eci.agronomo.farm.service.ranch;

import edu.eci.agronomo.farm.model.ranch.Ranch;
import edu.eci.agronomo.farm.model.ranch.RanchDto;
import edu.eci.agronomo.farm.respository.ranch.RanchMongoRepository;
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
public class RanchServiceImplTest {

    @MockBean
    private RanchMongoRepository ranchMongoRepository;
    @Autowired
    private RanchService ranchService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){ mockMvc = standaloneSetup(ranchService).build();}

    @Test
    public void testFindByIdExistingFarm() throws Exception {
        Ranch ranch = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        when(ranchMongoRepository.findById("1")).thenReturn(Optional.of(ranch));

        Ranch ranchTest = ranchService.getById("1").orElseThrow();

        Assertions.assertEquals(ranch, ranchTest);

        verify(ranchMongoRepository, times(1)).findById("1");
    }

    @Test
    public void testFindAllFarms() throws Exception {
        Ranch ranch = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Ranch ranch2 = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Ranch ranch3 = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        Ranch ranch4 = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);

        List<Ranch> ranchList = new ArrayList<>();

        ranchList.add(ranch);
        ranchList.add(ranch2);
        ranchList.add(ranch3);
        ranchList.add(ranch4);
        when(ranchMongoRepository.findAll()).thenReturn(ranchList);

        List<Ranch> testFarms = ranchService.getAll();

        Assertions.assertEquals(ranchList, testFarms);

        verify(ranchMongoRepository, times(1)).findAll();
    }

    @Test
    public void testCreateFarm() throws Exception {
        Ranch ranch = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        when(ranchMongoRepository.save(ranch)).thenReturn(ranch);

        Ranch testFarm = ranchService.create(ranch);

        Assertions.assertEquals(ranch, testFarm);

        verify(ranchMongoRepository, times(1)).save(ranch);
    }

    @Test
    public void testDeleteFarm() throws Exception {
        Ranch ranch = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);

        ranchService.deleteById("1");

        verify(ranchMongoRepository, times(1)).deleteById("1");
    }

    @Test
    public void testUpdateFarm() throws Exception {
        Ranch ranch = new Ranch("1", "Ada", "Ganado", "Cogua", (float) 0.22321);
        RanchDto ranchDto = new RanchDto("1", "Ada2", "Ganado", "Cogua", new ArrayList<>('1') ,(float) 0.22321);
        Ranch ranch2 = new Ranch("1", "Ada2", "Ganado", "Cogua", (float) 0.22321);

        when(ranchMongoRepository.findById("1")).thenReturn(Optional.of(ranch));
        when(ranchMongoRepository.save(ranch)).thenReturn(ranch2);
        Ranch saved = ranchService.update("1", ranchDto);

        Assertions.assertEquals(ranch2.getName(), saved.getName());

        verify(ranchMongoRepository, times(1)).save(ranch);
    }
}
