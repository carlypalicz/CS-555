import java.util.*;

public class Family{
    String id;
    Person husband;
    Person wife;
    Date marriage;
    boolean isDivorced;
    Date divorce;
    List<Person> children;
    public Family(String id){
        this.id = id;
        children = new ArrayList<Person>();
        isDivorced = false;
    }

    public void addMarriage(Date d) throws Exception{
        marriage = d;
        if(husband.getBirth().after(marriage)|| wife.getBirth().after(marriage)){
            // throw new Exception("No birth after marriage");
        }
        Date husDeath = husband.getDeath();
        if(husDeath != null && husDeath.before(marriage)){
            // throw new Exception("No death before marriage");
        }
        Date wifDeath = wife.getDeath();
        if(wifDeath != null && wifDeath.before(marriage)){
            // throw new Exception("No death before marriage");
        }
    }
    public void addHusband(Person husband){
        this.husband = husband;
    }
    public void addWife(Person wife){
        this.wife = wife;
    }

    
    public void addChild(Person child){
        children.add(child);
    }
    public List<Person> getChildren(){
        return children;
    }

    public Person getHusband(){
        return husband;
    }

    public Person getWife(){
        return wife;
    }

    public Date getMarriage(){
        return marriage;
    }

    public void divorce(Date div){
        isDivorced = true;
        divorce = div;
    }

    public Date getDivorce(){
        if(isDivorced){
            return divorce;
        }
        else{
            return null;
        }
    }
}