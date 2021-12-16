package de.tech26.robotfactory.Controller;


//import de.tech26.robotfactory.Service.RobotFactoryService;
import de.tech26.robotfactory.Model.Product;
import de.tech26.robotfactory.Repository.RobotRepository;
import de.tech26.robotfactory.Service.RobotTempService;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;


@RestController
public class RobotFactoryController {

    //Logger logger = LoggerFactory.getLogger(RobotFactoryController.class);

    @Autowired
    RobotTempService rts;
    //RobotFactoryService rfc;

    @Autowired
    RobotRepository rp;

    @RequestMapping(
            value = "/orders",
            method = RequestMethod.POST,
            consumes = "application/json"
    )

    public ResponseEntity assignRobot(@Valid @RequestBody Map<String, List> payLoad)  {

        ResponseEntity rs = null;
        List componentList = payLoad.get("component");
        try {

            Map response = rts.validateAndGetCost(componentList);
            rs = new ResponseEntity( response, HttpStatus.CREATED);
            return rs;
        }
        catch (Exception e){
            e.getMessage();
            ResponseEntity rserror = new ResponseEntity("Unprocessable Request", HttpStatus.UNPROCESSABLE_ENTITY);
            return rserror;
        }

    }

    /*@GetMapping("/getRepo")
    public Map getRoboRepo(@Valid @RequestBody Map<String, List> payLoad) throws DataFormatException, ParseException, JsonProcessingException {


        return rpd.getRepo();

    }*/
}

