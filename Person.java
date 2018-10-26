import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;

public class Person {
    String name;
    String id;
    Date born;
    boolean isDead;
    Date died;

    public Person(String id) {
        this.id = id;
    }

    public void addName(String name) {
        this.name = name;
    }

    public void addBirthDate(Date birthday) {
        born = birthday;
    }

    public void kill(Date deathDate){
        
        isDead = true;
        died = deathDate;
    }

    public Date getDeath() {
        if (isDead) {
            return died;
        } else {
            return null;
        }
    }

    public Date getBirth() {
        return born;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getAgeAtDate(Date check){
        if(check.before(born)){
            return -1;
        }
        Calendar birthdate = Calendar.getInstance(Locale.US);
        birthdate.setTime(born);
        Calendar checkdate = getInstance(Locale.US);
        checkdate.setTime(check);
        int age = checkdate.get(YEAR) - birthdate.get(YEAR);
        if(birthdate.get(MONTH) > checkdate.get(MONTH) || (checkdate.get(MONTH) == birthdate.get(MONTH) && birthdate.get(DATE)>checkdate.get(DATE))){
            age--;
        }
        return age;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String output = "ID: " + id + " Name: " + name + "  Born: " + sdf.format(born);
        if (isDead) {
            output += "  Died: " + sdf.format(died);
        }
        return output;
    }
}
