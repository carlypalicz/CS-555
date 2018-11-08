
/**
 * Carly Palicz
 * I pledge my honor that I have abided by the Stevens Honor System
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class TestS3A {

    public static void main(String[] args) throws Exception {
        String file1 = "TestFiles/testerfam.ged";
        String file2 = "TestFiles/sprint3a1.ged";
        List<Person> individuals = new ArrayList<Person>();
        List<Family> families = new ArrayList<Family>();
        List<Date> dates = new ArrayList<Date>();
        List<String> errors = new ArrayList<String>();

        System.out.println("Case 1:" + file1 + "\nExpected Behavior: No Errors");
        ReadGedcom.parse(file1, individuals, families, dates);
        FindErrors.UniqueFirstNameInFamilies(errors, families);

        System.out.println("Errors Found: ");
        for (int j = 0; j < errors.size(); j++) {
            System.out.println("E" + j + ": " + errors.get(j));
        }

        individuals.clear();
        families.clear();
        dates.clear();
        errors.clear();

        System.out.println("\nCase 2:" + file2 + "\nExpected Behavior: 2 Errors, same first name in family");
        ReadGedcom.parse(file2, individuals, families, dates);
        FindErrors.UniqueFirstNameInFamilies(errors, families);

        System.out.println("Errors Found: ");
        for (int j = 0; j < errors.size(); j++) {
            System.out.println("E" + j + ": " + errors.get(j));
        }

        individuals.clear();
        families.clear();
        dates.clear();
        errors.clear();

    }
}
