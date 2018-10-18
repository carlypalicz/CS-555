/**
 * Isabelle Joyce, Carly Palicz, Julia Martin
 * We pledge our honor that we have abided by the Stevens Honor System
 * Updated 10/17/2018
 */

import java.io.*;
import java.util.*;
import java.text.*;
public class Project3{

    public static void main(String[] args) throws Exception{
        String fileName = "My-Family.ged";
        List<Person> individuals = new ArrayList<Person>();
        List<Family> families = new ArrayList<Family>();
        List<String> errors = new ArrayList<String>();

        ReadGedcom.parse(fileName, individuals, families);
        FindErrors.birthBeforeMarriage(errors, families);
        FindErrors.marriageBeforeDeath(errors, families);

        System.out.println("Information found in "+fileName+"\n-----\n");

        System.out.println("Individuals:");
        for(int j = 0; j<individuals.size();j++){
            System.out.println(individuals.get(j));
        }

        System.out.println("\nFamilies:");
        for(int j = 0; j<families.size();j++){
            System.out.println(families.get(j));
        }

        System.out.println("\nErrors: ");
        for(int j = 0; j<errors.size();j++){
            System.out.println("E"+j+": "+errors.get(j));
        }
    }
}
