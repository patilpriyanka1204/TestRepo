package de.tech26.robotfactory.Controller;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;

public class RequestData implements ConstraintValidator<InputValidatorInterface, Map> {




    @Override
    public boolean isValid(Map value, ConstraintValidatorContext context) {
        if(!value.isEmpty() && value.containsKey("component") ){
          List arrList = (List) value.get("component");
          if(!arrList.isEmpty() && arrList.size() > 0 ){
              return true;
          }
        }
        return false;
    }




}
