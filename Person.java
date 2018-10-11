import java.util.*;

public class Person{
    String name;
    String id;
    Date born;
    boolean isDead;
    Date died;
    public Person(String name, String id, Date d){
        this.name = name;
        this.id = id;
        this.born = d;
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

}