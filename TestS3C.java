
/**
 * Carly Palicz
 * I pledge my honor that I have abided by the Stevens Honor System
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class TestS3C {

    public static void main(String[] args) throws Exception {
        String file1 = "TestFiles/testerfam.ged";
        String file2 = "TestFiles/sprint3c1.ged";
        List<Person> individuals = new ArrayList<Person>();
        List<Family> families = new ArrayList<Family>();
        List<Date> dates = new ArrayList<Date>();
        List<String> errors = new ArrayList<String>();

        System.out.println("Case 1:" + file1 + "\nExpected Behavior: Age 38, Death Date / No current age, Age 16");
        ReadGedcom.parse(file1, individuals, families, dates);

        System.out.println("Individuals:");
        for (int j = 0; j < individuals.size(); j++) {
            System.out.println(individuals.get(j));
        }

        individuals.clear();
        families.clear();
        dates.clear();
        errors.clear();

        System.out.println("\nCase 2:" + file2 + "\nExpected Behavior: Age 68, Age 118, Death Date / No current age");
        ReadGedcom.parse(file2, individuals, families, dates);

        System.out.println("Individuals:");
        for (int j = 0; j < individuals.size(); j++) {
            System.out.println(individuals.get(j));
        }

        individuals.clear();
        families.clear();
        dates.clear();
        errors.clear();

    }
}
