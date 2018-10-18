import java.io.*;
import java.util.*;
import java.text.*;

public class FindErrors{
    public static void birthBeforeMarriage(List<String> errors, List<Family> families){
        Person husband, wife = null;
        String errorMessage = "";
        for(Family fam:families){
            husband = fam.getHusband();
            wife = fam.getWife();
            if(husband.getBirth().after(fam.getMarriage())){
                errorMessage = "Birth date of "+husband.getName()+" ("+husband.getId()+") occurs after his marriage date in family "+fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
            
            if(wife.getBirth().after(fam.getMarriage())){
                errorMessage = "Birth date of "+wife.getName()+" ("+wife.getId()+") occurs after her marriage date in family "+fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void marriageBeforeDeath(List<String> errors, List<Family> families){
        Person husband, wife = null;
        Date death = null;
        String errorMessage = "";
        for(Family fam:families){
            husband = fam.getHusband();
            wife = fam.getWife();
            death = husband.getDeath();
            if(death!=null && death.before(fam.getMarriage())){
                errorMessage = "Death date of "+husband.getName()+" ("+husband.getId()+") occurs before his marriage date in family "+fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }

            death = wife.getDeath();
            if(death!=null && death.before(fam.getMarriage())){
                errorMessage = "Death date of "+wife.getName()+" ("+wife.getId()+") occurs before her marriage date in family "+fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }
}