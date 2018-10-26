import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
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
        Date marriage, divorce = null;
        String errorMessage = "";
        Person husband, wife = null;
        List<Person> children;
        int NINE_MONTHS_IN_DAYS = 273;

        for (Family fam : families) {
            husband = fam.getHusband();
            wife = fam.getWife();
            children = fam.getChildren();
            marriage = fam.getMarriage();
            divorce = fam.getDivorce();

            Date childsBirth = null;
            for (Person child : children) {
                childsBirth = child.getBirth();
                if (divorce != null && divorce.before(childsBirth)) {
                    long diffInMillis = Math.abs(childsBirth.getTime() - divorce.getTime());
                    long timeDiff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                    if (timeDiff > NINE_MONTHS_IN_DAYS) {
                        errorMessage = "Birth Date of " + child.getName() + " (" + child.getId()
                                + ") occurs too far after the divorce of " + wife.getName() + " (" + wife.getId()
                                + ") and " + husband.getName() + " (" + husband.getId() + ")";
                        errors.add(errorMessage);
                        errorMessage = "";
                    }
                } else if (childsBirth.before(marriage)) {
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
        int NINE_MONTHS_IN_DAYS = 273;

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
                    long diffInMillis = Math.abs(childsBirth.getTime() - dadDeath.getTime());
                    long timeDiff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                    if (timeDiff > NINE_MONTHS_IN_DAYS) {

                        errorMessage = "Birth Date of " + child.getName() + " (" + child.getId()
                                + ") occurs more than 9 months after death of " + husband.getName() + " ("
                                + husband.getId() + ")";
                        errors.add(errorMessage);
                        errorMessage = "";
                    }
                }
            }
        }
    }
}