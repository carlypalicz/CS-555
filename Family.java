import java.util.*;

public class Family{
    Person husband;
    Person wife;
    Date marriage;
    boolean isDivorced;
    Date divorce;
    List<Person> children;
    public Family(Person husband, Person wife, Date marriage) throws Exception{
        this.husband = husband;
        this.wife = wife;
        children = new ArrayList<Person>();
        isDivorced = false;
        this.marriage=marriage;
        if(this.husband.getBirth().after(marriage)|| this.wife.getBirth().after(marriage)){
            throw new Exception("No birth after marriage");
        }
        Date husDeath = this.husband.getDeath();
        if(husDeath != null && husDeath.before(marriage)){
            throw new Exception("No death before marriage");
        }
        Date wifDeath = this.wife.getDeath();
        if(wifDeath != null && wifDeath.before(marriage)){
            throw new Exception("No death before marriage");
        }
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