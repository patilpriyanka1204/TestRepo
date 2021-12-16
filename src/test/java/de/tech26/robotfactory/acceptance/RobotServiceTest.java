package de.tech26.robotfactory.acceptance;


import com.sun.media.sound.InvalidDataException;
import de.tech26.robotfactory.Model.Product;
import de.tech26.robotfactory.Repository.RobotRepository;
import de.tech26.robotfactory.Service.RobotTempService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class RobotServiceTest {

    @Mock
    private RobotRepository userRepository;

    @InjectMocks
    private RobotTempService robotTempService;

    private Map numberOfPart ;

    @BeforeEach
    void setmapValue(){
        numberOfPart = new HashMap<String,List>();
        ArrayList<String> arrList = new ArrayList<String>() {
            {
                add("A");
                add("B");
                add("C");
            }
        };
        numberOfPart.put("Face",arrList);
        ArrayList<String> arrList1 = new ArrayList<String>() {
            {
                add("D");
                add("E");

            }
        };
        numberOfPart.put("Arms",arrList1);
        ArrayList<String> arrList2 = new ArrayList<String>() {
            {
                add("F");
                add("G");
                add("H");

            }
        };
        numberOfPart.put("Mobility",arrList2);
        ArrayList<String> arrList3 = new ArrayList<String>() {
            {
                add("I");
                add("J");

            }
        };
        numberOfPart.put("Material",arrList3);
    }

    @Test
    void shouldSavetheValue() throws Throwable {
        final List parts = new ArrayList();
            parts.add("I");
            parts.add("A");
            parts.add("D");
            parts.add("F");

        Product pro = new Product();
        pro.setAvailable(1);
        pro.setPrice(160.11);
        pro.setCode("A");
        pro.setPart("Hands");
        Product[] p = new Product[]{pro
        };
        Mockito.when(userRepository.getnumberOfParts()).thenReturn(numberOfPart);
        Mockito.when(userRepository.getRepo()).thenReturn(p);

            Map maptest = robotTempService.validateAndGetCost(parts);
            Map mapresult = new HashMap<String,Number>();
        mapresult.put("total",160.11);
        mapresult.put("order_id",org.hamcrest.CoreMatchers.notNullValue());

        Assertions.assertEquals(mapresult.size(),maptest.size());
    }

    @Test
    void shouldThrowException(){

        InvalidDataException exception = Assertions.assertThrows(InvalidDataException.class,() ->{
            final List parts = new ArrayList();
            parts.add("I");
            parts.add("A");
            parts.add("D");
            parts.add("F");
            parts.add("H");


            Product pro = new Product();
            pro.setAvailable(1);
            pro.setPrice(160.11);
            pro.setCode("A");
            pro.setPart("Hands");
            Product[] p = new Product[]{pro
            };
            Mockito.when(userRepository.getnumberOfParts()).thenReturn(numberOfPart);

            Map maptest = robotTempService.validateAndGetCost(parts);

        });

        Assertions.assertEquals("validation failed", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionForMissing(){

        InvalidDataException exception = Assertions.assertThrows(InvalidDataException.class,() ->{
            final List parts = new ArrayList();
            parts.add("I");
            parts.add("A");
            parts.add("D");
            parts.add("A");

            Product pro = new Product();
            pro.setAvailable(1);
            pro.setPrice(160.11);
            pro.setCode("A");
            pro.setPart("Hands");
            Product[] p = new Product[]{pro
            };
            Mockito.when(userRepository.getnumberOfParts()).thenReturn(numberOfPart);
            Map maptest = robotTempService.validateAndGetCost(parts);

        });

        Assertions.assertEquals("validation failed", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInsuficientData(){


        InvalidDataException exception = Assertions.assertThrows(InvalidDataException.class,() ->{
            final List parts = new ArrayList();
            parts.add("I");
            parts.add("C");
            parts.add("D");
            parts.add("G");

            
            Product pro = new Product();
            pro.setAvailable(0);
            pro.setPrice(160.11);
            pro.setCode("I");
            pro.setPart("Hands");

            Product pro1 = new Product();
            pro.setAvailable(2);
            pro.setPrice(160.11);
            pro.setCode("A");
            pro.setPart("Hands");

            Product pro2 = new Product();
            pro.setAvailable(4);
            pro.setPrice(160.11);
            pro.setCode("D");
            pro.setPart("Hands");

            Product pro3 = new Product();
            pro.setAvailable(7);
            pro.setPrice(160.11);
            pro.setCode("C");
            pro.setPart("Hands");
            Product[] p = new Product[]{pro,pro1,pro2,pro3
            };
            Mockito.when(userRepository.getnumberOfParts()).thenReturn(numberOfPart);
            Mockito.when(userRepository.getRepo()).thenReturn(p);
            Map maptest = robotTempService.validateAndGetCost(parts);

        });

        Assertions.assertEquals("No Data Available", exception.getMessage());
        //robotTempService.validateAndGetCost(parts);


        //Assertions.assertThrows(new InvalidDataException(),robotTempService.validateAndGetCost(parts));
    }
}
