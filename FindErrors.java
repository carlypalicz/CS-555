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
                errorMessage = "ERROR: US02: Birth date of " + husband.getName() + " (" + husband.getId()
                        + ") occurs after his marriage date in family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }

            if (wife.getBirth().after(fam.getMarriage())) {
                errorMessage = "ERROR: US02: Birth date of " + wife.getName() + " (" + wife.getId()
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
                errorMessage = "ERROR: US05: Death date of " + husband.getName() + " (" + husband.getId()
                        + ") occurs before his marriage date in family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }

            death = wife.getDeath();
            if (death != null && death.before(fam.getMarriage())) {
                errorMessage = "ERROR: US05: Death date of " + wife.getName() + " (" + wife.getId()
                        + ") occurs before her marriage date in family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void marriageBeforeDivorce(List<String> errors, List<Family> families) {
        String errorMessage = "";
        Date marriage, divorce;
        Person husband, wife = null;
        for (Family fam : families) {
            marriage = fam.getMarriage();
            divorce = fam.getDivorce();
            husband = fam.getHusband();
            wife = fam.getWife();
            if (divorce != null && marriage != null && divorce.before(marriage)) {
                errorMessage = "ERROR: US04: Divorce of " + husband.getName() + " and " + wife.getName() + " ("
                        + husband.getId() + " and " + wife.getId() + ") occurs before their marriage date in family "
                        + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void divorceBeforeDeath(List<String> errors, List<Family> families) {
        Person husband, wife = null;
        Date death = null;
        String errorMessage = "";
        Date marriage, divorce;
        for (Family fam : families) {
            husband = fam.getHusband();
            wife = fam.getWife();
            marriage = fam.getMarriage();
            divorce = fam.getDivorce();

            if (marriage != null && divorce != null) {
                death = husband.getDeath();
                if (death != null && death.before(fam.getDivorce())) {
                    errorMessage = "ERROR: US06: Death date of " + husband.getName() + " (" + husband.getId()
                            + ") occurs before his divorce date in family " + fam.getId();
                    errors.add(errorMessage);
                    errorMessage = "";
                }

                death = wife.getDeath();
                if (death != null && death.before(fam.getDivorce())) {
                    errorMessage = "ERROR: US06: Death date of " + wife.getName() + " (" + wife.getId()
                            + ") occurs before her divorce date in family " + fam.getId();
                    errors.add(errorMessage);
                    errorMessage = "";
                }
            }
        }
    }

    public static void dateBeforeCurrentDate(List<String> errors, List<Date> dates) {
        String errorMessage = "";
        for (Date newDate : dates) {
            Date todaysDate = new Date();
            if (todaysDate.before(newDate)) {
                errorMessage = "ERROR: US01: The date " + newDate + " takes place in the future.";
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
                errorMessage = "ERROR: US03: Death Date of " + person.getName() + " (" + person.getId() + ")"
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
                        errorMessage = "ERROR: US08: Birth Date of " + child.getName() + " (" + child.getId()
                                + ") occurs too far after the divorce of " + wife.getName() + " (" + wife.getId()
                                + ") and " + husband.getName() + " (" + husband.getId() + ")";
                        errors.add(errorMessage);
                        errorMessage = "";
                    }
                } else if (childsBirth.before(marriage)) {
                    errorMessage = "ERROR: US08: Birth Date of " + child.getName() + " (" + child.getId()
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
                    errorMessage = "ERROR: US09: Birth Date of " + child.getName() + " (" + child.getId()
                            + ") occurs before death of " + wife.getName() + " (" + wife.getId() + ")";
                    errors.add(errorMessage);
                    errorMessage = "";
                }

                else if ((dadDeath != null) && (dadDeath.before(childsBirth))) {
                    long diffInMillis = Math.abs(childsBirth.getTime() - dadDeath.getTime());
                    long timeDiff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                    if (timeDiff > NINE_MONTHS_IN_DAYS) {

                        errorMessage = "ERROR: US09: Birth Date of " + child.getName() + " (" + child.getId()
                                + ") occurs more than 9 months after death of " + husband.getName() + " ("
                                + husband.getId() + ")";
                        errors.add(errorMessage);
                        errorMessage = "";
                    }
                }
            }
        }
    }

    public static void siblingSpacing(List<String> errors, List<Family> families) {
        List<Person> children = null;
        int EIGHT_MONTHS_IN_DAYS = 243;
        String errorMessage = "";
        for (Family fam : families) {
            children = fam.getChildren();
            for (Person child1 : children) {
                for (Person child2 : children) {
                    long diffInMillis = Math.abs(child1.getBirth().getTime() - child2.getBirth().getTime());
                    long timeDiff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                    if (timeDiff > 2 && timeDiff < EIGHT_MONTHS_IN_DAYS) {
                        errorMessage = "ERROR: US13: Birth date of " + child1.getName() + " (" + child1.getId()
                                + ") is too close to birth date of " + child2.getName() + " (" + child2.getId()
                                + ") in Family " + fam.getId();
                        errors.add(errorMessage);
                        errorMessage = "";
                    }
                }
            }
        }
    }

    public static void maximumSiblings(List<String> errors, List<Family> families) {
        List<Person> children = null;
        String errorMessage = "";
        for (Family fam : families) {
            children = fam.getChildren();
            if (children.size() > 15) {
                errorMessage = "ERROR: US15: Family " + fam.getId() + " has more than 15 children";
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void LessThanHundredFifty(List<String> errors, List<Person> individuals) {
        String errorMessage = "";
        Date currentDate = new Date();
        int age;
        for (Person p : individuals) {
            if (p.isDead) {
                age = p.getAgeAtDate(p.getDeath());
            } else {
                age = p.getAgeAtDate(currentDate);
            }
            if (age > 150) {
                errorMessage = "ERROR: US07: Individual " + p.getName() + " (" + p.getId() + ") is over 150 years old";
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    public static void MarriageAfterFourteen(List<String> errors, List<Family> families) {
        Person husband, wife = null;
        Date marriage = null;
        String errorMessage;
        for (Family fam : families) {
            husband = fam.getHusband();
            wife = fam.getWife();
            marriage = fam.getMarriage();
            if (husband.getAgeAtDate(marriage) < 14) {
                errorMessage = "ERROR: US10: Individual " + husband.getName() + " (" + husband.getId()
                        + ") was under 14 at the date of his marriage in Family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
            if (wife.getAgeAtDate(marriage) < 14) {
                errorMessage = "ERROR: US10: Individual " + wife.getName() + " (" + wife.getId()
                        + ") was under 14 at the date of her marriage in Family " + fam.getId();
                errors.add(errorMessage);
                errorMessage = "";
            }
        }
    }

    /**
     * there should not be more than one child with the same first name and birthday
     * in a family. if there is, add to errors list
     */
    public static void UniqueFirstNameInFamilies(List<String> errors, List<Family> families) {
        String errorMessage;
        List<Person> children = null;

        for (Family fam : families) {
            children = fam.getChildren();
            Date birthday1, birthday2 = null;
            String firstname1, firstname2;

            for (Person child1 : children) {
                for (Person child2 : children) {
                    birthday1 = child1.getBirth();
                    birthday2 = child2.getBirth();
                    int lastNameStart1 = (child1.getName()).lastIndexOf(' ');
                    int lastNameStart2 = (child2.getName()).lastIndexOf(' ');
                    firstname1 = child1.getName().substring(0, lastNameStart1);
                    firstname2 = child2.getName().substring(0, lastNameStart2);

                    if (firstname1.equals(firstname2) && (birthday1.compareTo(birthday2) == 0)
                            && ((child1.getId()).compareTo(child2.getId()) != 0)) {
                        errorMessage = "ERROR: US25: Child " + child1.getName() + " (" + child1.getId() + ") and child "
                                + child2.getName() + " (" + child2.getId()
                                + ") share the same first name and the same birthday, " + child1.getBirth();
                        errors.add(errorMessage);
                        errorMessage = "";
                    }
                }
            }
        }
    }
}