import java.io.*;
import java.util.*;
import java.text.*;

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
}