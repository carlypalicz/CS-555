import java.io.*;
import java.util.*;
import java.text.*;
import java.util.concurrent.TimeUnit;

public class FindErrors {
    public static void birthBeforeMarriage(List<String> errors, List<Family> families) {
        Person husband, wife = null;
        String errorMessage = "";
        for (Family fam : families) {
            husband = fam.getHusband();
            wife = fam.getWife();
            if (husband.getBirth().after(fam.getMarriage())) {
                errorMessage = "Birth date of " + husband.getName() + " (" + husband.getId()
                        + ") occurs after his marriage date in family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }

            if (wife.getBirth().after(fam.getMarriage())) {
                errorMessage = "Birth date of " + wife.getName() + " (" + wife.getId()
                        + ") occurs after her marriage date in family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void marriageBeforeDeath(List<String> errors, List<Family> families) {
        Person husband, wife = null;
        Date death = null;
        String errorMessage = "";
        for (Family fam : families) {
            husband = fam.getHusband();
            wife = fam.getWife();
            death = husband.getDeath();
            if (death != null && death.before(fam.getMarriage())) {
                errorMessage = "Death date of " + husband.getName() + " (" + husband.getId()
                        + ") occurs before his marriage date in family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }

            death = wife.getDeath();
            if (death != null && death.before(fam.getMarriage())) {
                errorMessage = "Death date of " + wife.getName() + " (" + wife.getId()
                        + ") occurs before her marriage date in family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void dateBeforeCurrentDate(List<String> errors, List<Date> dates) {
        String errorMessage = "";
        for (Date newDate : dates) {
            Date todaysDate = new Date();
            if (todaysDate.before(newDate)) {
                errorMessage = "The date " + newDate + " takes place in the future.";
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void birthBeforeDeath(List<String> errors, List<Person> individuals) {
        Date birth, death = null;
        String errorMessage = "";
        Person currentPerson = null;

        for (Person person : individuals) {
            birth = person.getBirth();
            death = person.getDeath();

            if (death != null && death.before(birth)) {
                errorMessage = "Death Date of " + person.getName() + " (" + person.getId() + ")"
                        + " occurs before birth date";
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void birthBeforeMarriageOfParents(List<String> errors, List<Family> families) {
        Date marriage = null;
        String errorMessage = "";
        Person husband, wife = null;
        List<Person> children;

        for (Family fam : families) {
            husband = fam.getHusband();
            wife = fam.getWife();
            children = fam.getChildren();
            marriage = fam.getMarriage();

            Date childsBirth = null;
            for (Person child : children) {
                childsBirth = child.getBirth();
                if (childsBirth.before(marriage)) {
                    errorMessage = "Birth Date of " + child.getName() + " (" + child.getId()
                            + ") occurs before marriage of " + wife.getName() + " (" + wife.getId() + ") and "
                            + husband.getName() + " (" + husband.getId() + ")";
                    errors.add(errorMessage);
                    errorMessage = "";
                }
            }
        }
    }

    public static void birthBeforeDeathOfParents(List<String> errors, List<Family> families) {
        Date momDeath, dadDeath = null;
        String errorMessage = "";
        Person husband, wife = null;
        List<Person> children;

        for (Family fam : families) {
            husband = fam.getHusband();
            wife = fam.getWife();
            children = fam.getChildren();
            momDeath = wife.getDeath();
            dadDeath = husband.getDeath();

            Date childsBirth = null;
            for (Person child : children) {
                childsBirth = child.getBirth();
                if ((momDeath != null) && (momDeath.before(childsBirth))) {
                    errorMessage = "Birth Date of " + child.getName() + " (" + child.getId()
                            + ") occurs before death of " + wife.getName() + " (" + wife.getId() + ")";
                    errors.add(errorMessage);
                    errorMessage = "";
                }

                else if ((dadDeath != null) && (dadDeath.before(childsBirth))) {
                    errorMessage = "Birth Date of " + child.getName() + " (" + child.getId()
                            + ") occurs before death of " + husband.getName() + " (" + husband.getId() + ")";
                    errors.add(errorMessage);
                    errorMessage = "";
                }
            }
        }
    }
    public static void siblingSpacing(List<String> errors, List<Family> families){
        List<Person> children = null;
        int EIGHT_MONTHS_IN_DAYS = 243;
        String errorMessage = "";
        for(Family fam:families){
            children = fam.getChildren();
            for(Person child1:children){
                for(Person child2: children){
                    long diffInMillis = Math.abs(child1.getBirth().getTime()-child2.getBirth().getTime());
                    long timeDiff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                        if(timeDiff>2 && timeDiff<EIGHT_MONTHS_IN_DAYS){
                            errorMessage = "Birth date of " + child1.getName() + " ("+child1.getId()
                            + ") is too close to birth date of " + child2.getName() + " ("+child2.getId() + ") in Family "+fam.getId();
                            errors.add(errorMessage);
                            errorMessage = "";
                        }
                }
            }
        }
    }

    public static void maximumSiblings(List<String> errors, List<Family> families){
        List<Person> children = null;
        String errorMessage = "";
        for(Family fam: families){
            children = fam.getChildren();
            if(children.size()>15){
                errorMessage = "Family "+fam.getId()+" has more than 15 children";
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void LessThanHundredFifty(List<String> errors, List<Person> individuals){
        String errorMessage = "";
        Date currentDate = new Date();
        int age;
        for(Person p:individuals){
            if(p.isDead){
                age = p.getAgeAtDate(p.getDeath());
            }
            else{
                age = p.getAgeAtDate(currentDate);
            }
            if(age>150){
                errorMessage = "Individual "+p.getName()+" ("+p.getId()+") is over 150 years old";
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void MarriageAfterFourteen(List<String> errors, List<Family> families){
        Person husband, wife = null;
        Date marriage = null;
        String errorMessage;
        for(Family fam: families){
            husband = fam.getHusband();
            wife = fam.getWife();
            marriage = fam.getMarriage();
            if(husband.getAgeAtDate(marriage)<14){
                errorMessage = "Individual "+husband.getName()+" ("+husband.getId()+") was under 14 at the date of his marriage in Family "+fam.getId();
                errors.add(errorMessage);
            }
            if(wife.getAgeAtDate(marriage)<14){
                errorMessage = "Individual "+wife.getName()+" ("+wife.getId()+") was under 14 at the date of her marriage in Family "+fam.getId();
                errors.add(errorMessage);
            }
        }
    }
}