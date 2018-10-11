/**
 * Isabelle Joyce
 * I pledge my honor that I have abided by the Stevens Honor System
 * 9/19/2018
 */

import java.io.*;
import java.util.*;
import java.text.*;
public class Project3{
    public static void main(String[] args) throws Exception{

        FileReader fr = new FileReader("My-Family.ged");
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        String tag, arguments;

        List<Person> individuals = new ArrayList<Person>();
        List<Family> families = new ArrayList<Family>();
        List<String> ids = new ArrayList<String>();
        Map<String, String> nameToId = new HashMap<String, String>();
        List<String> fams = new ArrayList<String>();
        Map<String, String[]> spouses = new HashMap<String, String[]>();
        String currentId = "";
        String currentName = "";
        String currentFam = "";
        String husb = "";
        String wife = "";
        Person currentPerson;
        Date currentDate;

        int i=2;
        while((line=br.readLine()) != null){
            while(i<line.length()&&line.charAt(i)!=' '){
                i++;
            }
            tag = line.substring(2, i);
            
            if((i+1)>=line.length()){
                arguments = "";
            }
            else{
                //everything after the second space is arguments
                arguments = line.substring(i+1);
            }

            if(!currentId.equals("")){
                if(tag.equals("NAME")){
                    currentName = arguments.replace("/","");
                    // nameToId.put(currentId, currentName);
                    currentId = "";
                    // currentName = "";
                }
                if(tag.equals("DATE")){

                }
            }
            else if(arguments.equals("INDI")){
                // a new individual
                // ids.add(tag);
                currentId = tag;
            }

            if(!currentFam.equals("")){
                if(tag.equals("HUSB")){
                    husb = arguments;
                }
                else if(tag.equals("WIFE")){
                    wife = arguments;

                }
                if(!husb.equals("") && !wife.equals("")){
                    String[] temp = {husb, wife};
                    spouses.put(currentFam, temp);
                    currentFam = husb = wife = "";
                }
            }
            else if(arguments.equals("FAM")){
                fams.add(tag);
                currentFam = tag;
            }

            i = 2;
        }
        //closes buffered reader
        br.close();

        System.out.println("INDIVIDUALS\n--\n\n");
        for(int j = 0; j<ids.size(); j++){
            System.out.println("ID: " + ids.get(j) + " Name: "+ nameToId.get(ids.get(j)));
        }
        System.out.println("FAMILIES\n--\n\n");
        for(int j = 0; j<fams.size(); j++){
            System.out.println("Family ID: "+fams.get(j));
            String h = spouses.get(fams.get(j))[0];
            System.out.println("Husband:");
            System.out.println("ID: "+ h +" Name: "+nameToId.get(h));
            String w = spouses.get(fams.get(j))[1];
            System.out.println("Wife:");
            System.out.println("ID: "+ w +" Name: "+nameToId.get(w));
            System.out.println("--");
        }
    }
}