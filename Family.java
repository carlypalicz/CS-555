import java.util.*;
import java.text.*;

public class Family {
    String id;
    Person husband;
    Person wife;
    Date marriage;
    boolean isDivorced;
    Date divorce;
    List<Person> children;

    public Family(String id) {
        this.id = id;
        children = new ArrayList<Person>();
        isDivorced = false;
    }

    public void addMarriage(Date marriageDate) {
        marriage = marriageDate;
    }

    public void addHusband(Person husband) {
        this.husband = husband;
    }

    public void addWife(Person wife) {
        this.wife = wife;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public List<Person> getChildren() {
        return children;
    }

    public Person getHusband() {
        return husband;
    }

    public Person getWife() {
        return wife;
    }

    public Date getMarriage() {
        return marriage;
    }

    public void divorce(Date divorceDate) {
        isDivorced = true;
        divorce = divorceDate;
    }

    public Date getDivorce() {
        if (isDivorced) {
            return divorce;
        } else {
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String output = "Family ID: " + id + "  Husband: " + husband.getName() + "  Wife: " + wife.getName()
                + "  Married: " + sdf.format(marriage);
        if (isDivorced) {
            output += "  Divorced: " + sdf.format(divorce);
        }
        if (children.size() != 0) {
            output += "  Children: ";
        }
        for (Person c : children) {
            output += c.getName() + ", ";
        }
        return output.substring(0, (output.length() - 2));
    }
}
