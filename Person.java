import java.text.SimpleDateFormat;
import java.util.*;

public class Person{
    String name;
    String id;
    Date born;
    boolean isDead;
    Date died;
    
    public Person(String id){
        this.id = id;
    }

    public void addName(String name){
        this.name = name;
    }

    public void addBirthDate(Date d){
        born = d;
    }

    public void kill(Date deathDate){
        isDead = true;
        died = deathDate;
    }

    public Date getDeath(){
        if(isDead){
            return died;
        }
        else{
            return null;
        }
    }

    public Date getBirth(){
        return born;
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String output = "ID: "+id+ " Name: "+name+"  Born: "+sdf.format(born);
        if(isDead){
            output+="  Died: "+sdf.format(died);
        }
        return output;
    }
}