
import java.io.*;
import java.util.*;
import java.text.*;

public class ReadGedcom {

    public static Person findIndiv(String id, List<Person> individuals) {
        for (Person p : individuals) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static void parse(String filename, List<Person> individuals, List<Family> families, List<Date> dates) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            String tag, arguments;
            int level;

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
                        currentPerson = new Person(tag);
                        individuals.add(currentPerson);
                    } else if (arguments.equals("FAM")) {
                        currentFam = new Family(tag);
                        families.add(currentFam);
                    }
                }
                if (currentPerson == null && currentFam == null) {

                } else if (tag.equals("NAME")) {
                    currentPerson.addName(arguments.replace("/", ""));
                } else if (tag.equals("BIRT")) {
                    dateFlag = "birth";
                } else if (tag.equals("DEAT")) {
                    dateFlag = "death";
                } else if (tag.equals("MARR")) {
                    dateFlag = "marriage";
                } else if (tag.equals("DIV")) {
                    dateFlag = "divorce";
                } else if (tag.equals("DATE")) {
                    currentDate = dateFormatter.parse(arguments);
                    dates.add(currentDate);

                    if (dateFlag.equals("birth")) {
                        currentPerson.addBirthDate(currentDate);
                        currentPerson.addCurrentAge(); // new in sprint 3
                    } else if (dateFlag.equals("death")) {
                        currentPerson.kill(currentDate);
                    } else if (dateFlag.equals("marriage")) {
                        currentFam.addMarriage(currentDate);
                    } else if (dateFlag.equals("divorce")) {
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
        } catch (

        FileNotFoundException f) {
            System.out.println("Could not find the file");
        } catch (Exception e) {
            System.out.println("Something else went wrong");
        }
    }
}
