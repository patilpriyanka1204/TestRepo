package de.tech26.robotfactory.Service;

import com.sun.media.sound.InvalidDataException;
import de.tech26.robotfactory.Model.Product;
import de.tech26.robotfactory.Repository.RobotRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.DataFormatException;

@Service
public class RobotTempService {

    @Autowired
    RobotRepository rp;

    private Map<String, List> listOfparts = new HashMap<>();

    public Map validateAndGetCost(List<String> parts) throws InvalidDataException {
        Map response = null;

            this.listOfparts = rp.getnumberOfParts();
            if (this.listOfparts.size() == parts.size() && checkforMissingPart(parts)) {
                try {
                    response = getCostAndOrderId(parts);
                }
                catch(Exception e){
                    throw new InvalidDataException(e.getMessage());
                }
            }
            else{
                throw new InvalidDataException("validation failed");
            }


        return response;
    }

    private Boolean checkforMissingPart(List parts) {

        for(Map.Entry map : listOfparts.entrySet()){
            List list = (List) map.getValue();
            long count = list.stream().filter(parts::contains).count();
            if(count > 1 || count == 0){
                return false;
            }
        }
        return true;

    }

    @NotNull
    @Contract("_ -> new")
    private Map<String,Number> getCostAndOrderId(List<String> parts) throws Exception {

        double total = 0;
        int order_id = 0;
        Map<String,Number> resultSet = new HashMap<String,Number>();
        rp.getRepo();
        Product[] products = rp.getRepo();

        for(Product p : products) {

            if (parts.contains(p.getCode()) && p.getAvailable() > 0 ) {
                total += p.getPrice();
                p.setAvailable(p.getAvailable() - 1);
            }
        }

        rp.updateAndSave(products);
        resultSet.put("order_id",Math.random());
        resultSet.put("total",total);

        return resultSet;
    }

}
