package de.tech26.robotfactory.Repository;

import de.tech26.robotfactory.Model.Product;

import java.util.List;
import java.util.Map;

public interface RobotRepositoryInter {

     Product[] getRepo();
     Map<String, List> getnumberOfParts();
     void updateAndSave(Product[] products);

}
