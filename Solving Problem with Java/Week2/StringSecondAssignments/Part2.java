
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;        
        while (stringb.contains(stringa)){
            stringb = stringb.replaceFirst(stringa,"");
            count++;
        }
        return count;
    }
    public void testHowMany(){
        String a = "ATGAGGTAG";
        String b = "ACATGAGGTAGCAATGAGGGTCGAGTAACTGATGAGGTCGAGTCACTGAGATGAGGTAGGTT";
        System.out.println(howMany(a,b));
    }
}
