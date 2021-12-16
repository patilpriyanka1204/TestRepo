package de.tech26.robotfactory.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;


import de.tech26.robotfactory.Model.Product;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.omg.CORBA.portable.OutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.xml.bind.DataBindingException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class RobotRepository implements RobotRepositoryInter {

    @Bean
    public void RobotRepository(){
        new RobotRepository();
    }

    @Value("classpath:${filepath}")
    String filepath;

    private ObjectMapper om = new ObjectMapper();

    public Product[] getRepo(){
        Product[] products;
        try {
            InputStream filepathLocal = new FileInputStream("src/main/resources/PartInventory.json");
            //System.out.println("file"+filepath);
           // InputStream input = new FileInputStream(getClass().getResourceAsStream("PartInventory.json"));
            products = om.readValue(filepathLocal, Product[].class);
            return products;
        }
        catch (Exception e){
            throw new DataBindingException(e);
        }

    }

    public Map<String,List> getnumberOfParts(){
        Map<String,List> numberOfPart = new HashMap<String,List>();
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

        return numberOfPart;

    }


    public void updateAndSave(Product[] products) {

        try {
           if(products.length !=0) {
               FileOutputStream output = new FileOutputStream("src/main/resources/PartInventory.json");
               om.writeValue(output, products);
           }
        }
        catch (Exception e){
            throw new DataBindingException(e);
        }
    }
}
