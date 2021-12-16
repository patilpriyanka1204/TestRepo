package de.tech26.robotfactory.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.media.sound.InvalidDataException;
import de.tech26.robotfactory.Model.Product;
import de.tech26.robotfactory.Repository.RobotRepository;
import de.tech26.robotfactory.Repository.RobotRepositoryInter;
import io.mockk.impl.annotations.InjectMockKs;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.DataBindingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class RobotRepositoryTest {

    @Mock
    ObjectMapper omtest;

    @Mock
    FileOutputStream file;

    @Autowired
    @InjectMocks
    RobotRepository  repo;

    @BeforeEach
    void checkFileExists()  {


            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource("PartInventory.json").getFile());
            org.junit.jupiter.api.Assertions.assertTrue(file.exists());



    }

    @Test
    void shouldGetRepo() {


        Product[] pro = repo.getRepo();
        Product[] p = new Product[]{};
        org.junit.jupiter.api.Assertions.assertEquals(pro.getClass(), p.getClass());

    }

    @Test
    void shouldCheckMapForRobot() {

        Map pro = repo.getnumberOfParts();

        Assertions.assertThat(pro.size() > 0);

    }

    @Test
    void shouldSaveData() throws IOException {

            Product pro = new Product();
            pro.setAvailable(1);
            pro.setPrice(160.11);
            pro.setCode("A");
            pro.setPart("Hands");
            Product[] p = new Product[]{pro
            };

                file = new FileOutputStream("src/main/resources/PartInventoryTest.json");
                

        Mockito.verify(omtest).writeValue(file,p);

    }


}
