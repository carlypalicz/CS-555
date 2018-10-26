/**
 * Isabelle Joyce, Carly Palicz, Julia Martin
 * We pledge our honor that we have abided by the Stevens Honor System
 * Updated 10/25/2018
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class TestS2F {

    public static void main(String[] args) throws Exception {
        String file1 = "TestFiles/testerfam.ged";
        String file2 = "TestFiles/sprint2f1.ged";
        List<Person> individuals = new ArrayList<Person>();
        List<Family> families = new ArrayList<Family>();
        List<Date> dates = new ArrayList<Date>();
        List<String> errors = new ArrayList<String>();

        

        System.out.println("Case 1:"+file1+"\nExpected Behavior: No Errors");
        ReadGedcom.parse(file1, individuals, families, dates);
        FindErrors.maximumSiblings(errors, families);

        System.out.println("Errors Found: ");
        for (int j = 0; j < errors.size(); j++) {
            System.out.println("E" + j + ": " + errors.get(j));
        }

        individuals.clear();
        families.clear();
        dates.clear();
        errors.clear();

        System.out.println("\nCase 2:"+file2+"\nExpected Behavior: 1 Error, more than 15 children");
        ReadGedcom.parse(file2, individuals, families, dates);
        FindErrors.maximumSiblings(errors, families);

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
