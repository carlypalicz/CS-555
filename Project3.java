
/**
 * Isabelle Joyce
 * I pledge my honor that I have abided by the Stevens Honor System
 * 9/19/2018
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class Project3 {

    public static Person findIndiv(String id, List<Person> individuals) {
        for (Person p : individuals) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        FileReader fr = new FileReader("My-Family.ged");
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        String tag, arguments;
        int level;
        String personFlag;

        List<Person> individuals = new ArrayList<Person>();
        List<Family> families = new ArrayList<Family>();
        Person currentPerson = null;
        Family currentFam = null;
        Date currentDate;
        String dateFlag = "";
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        int i = 2;

        while ((line = br.readLine()) != null) {
            level = line.charAt(0) - '0';
            while (i < line.length() && line.charAt(i) != ' ') {
                i++;
            }
            tag = line.substring(2, i);

            if ((i + 1) >= line.length()) {
                arguments = "";
            } else {
                // everything after the second space is arguments
                arguments = line.substring(i + 1);
            }

            if (level == 0) {
                if (arguments.equals("INDI")) {
                    System.out.println("new individual");
                    currentPerson = new Person(tag);
                    individuals.add(currentPerson);
                } else if (arguments.equals("FAM")) {
                    currentFam = new Family(tag);
                    families.add(currentFam);
                }
            }
            if (currentPerson == null && currentFamily == null) {

            } else if (tag.equals("NAME")) {
                currentPerson.addName(arguments.replace("/", ""));
            } else if (tag.equals("BIRT")) {
                dateFlag = "b";
            } else if (tag.equals("DEAT")) {
                dateFlag = "d";
            } else if (tag.equals("MARR")) {
                dateFlag = "m";
            } else if (tag.equals("DIV")) {
                dateFlag = "d";
            } else if (tag.equals("DATE")) {
                currentDate = dateFormatter.parse(arguments);
                // carly - currentDate is date ur adding and nowDate is the present actual day
                nowDate = new Date();
                if (nowDate.before(currentDate)) {
                    throw new Exception("A date cannot take place in the future")
                }
                // end carly

                if (dateFlag.equals("b")) {
                    currentPerson.addBirthDate(currentDate);
                } else if (dateFlag.equals("d")) {
                    currentPerson.kill(currentDate);
                } else if (dateFlag.equals("m")) {
                    currentFam.addMarriage(currentDate);
                } else if (dateFlag.equals("d")) {
                    currentFam.divorce(currentDate);
                }
            } else if (tag.equals("HUSB")) {
                currentFam.addHusband(findIndiv(arguments, individuals));
            } else if (tag.equals("WIFE")) {
                currentFam.addWife(findIndiv(arguments, individuals));
            } else if (tag.equals("CHIL")) {
                currentFam.addChild(findIndiv(arguments, individuals));
            }
            i = 2;
        }
        // closes buffered reader
        br.close();

        for (int j = 0; j < individuals.size(); j++) {
            System.out.println(individuals.get(j) + "\n");
        }
    }
}
