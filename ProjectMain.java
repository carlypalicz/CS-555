
/**
 * Isabelle Joyce, Carly Palicz, Julia Martin
 * We pledge our honor that we have abided by the Stevens Honor System
 * Updated 10/25/2018
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class ProjectMain {

    public static void main(String[] args) throws Exception {
        String fileName = "TestFiles/sprint3a1.ged";
        List<Person> individuals = new ArrayList<Person>();
        List<Family> families = new ArrayList<Family>();
        List<Date> dates = new ArrayList<Date>(); // new
        List<String> errors = new ArrayList<String>();

        ReadGedcom.parse(fileName, individuals, families, dates);
        FindErrors.birthBeforeMarriage(errors, families);
        FindErrors.marriageBeforeDeath(errors, families);
        FindErrors.marriageBeforeDivorce(errors, families);
        FindErrors.divorceBeforeDeath(errors, families);
        FindErrors.dateBeforeCurrentDate(errors, dates); // new
        FindErrors.birthBeforeDeath(errors, individuals); // new
        FindErrors.birthBeforeMarriageOfParents(errors, families); // new
        FindErrors.birthBeforeDeathOfParents(errors, families); // new
        FindErrors.siblingSpacing(errors, families);
        FindErrors.maximumSiblings(errors, families);
        FindErrors.LessThanHundredFifty(errors, individuals);
        FindErrors.MarriageAfterFourteen(errors, families);
        FindErrors.UniqueFirstNameInFamilies(errors, families);

        System.out.println("Information found in " + fileName + "\n-----\n");

        System.out.println("Individuals:");
        for (int j = 0; j < individuals.size(); j++) {
            System.out.println(individuals.get(j));
        }

        System.out.println("\nFamilies:");
        for (int j = 0; j < families.size(); j++) {
            System.out.println(families.get(j));
        }

        System.out.println("\nErrors: ");
        for (int j = 0; j < errors.size(); j++) {
            System.out.println("E" + j + ": " + errors.get(j));
        }
    }
}
