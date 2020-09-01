
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int subStringIdx = stringb.indexOf(stringa);
        int count = 0;
        
        while (subStringIdx >=0) {
            count++;
            subStringIdx = stringb.indexOf(stringa, subStringIdx + 1);
        }                  
        return count >= 2;
    }
    public String lastPart(String stringa, String stringb){
        int subStringIdx = stringb.indexOf(stringa);
        String remainderString = stringb;
        if (subStringIdx >= 0){
            remainderString = stringb.substring(subStringIdx + stringa.length());
        }
        return remainderString;
    }
    public void testString(){
        String stringa = "an";
        String stringb = "banana";
        System.out.println("LastPart: " + lastPart(stringa, stringb));
        
        stringa = "by";
        stringb = "A story by Abby Long";
        System.out.println("Two Occurrences: " + twoOccurrences(stringa, stringb));
    }
    
}
