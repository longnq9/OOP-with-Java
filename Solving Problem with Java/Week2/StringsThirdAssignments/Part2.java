
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;
public class Part2 {
    public double cgRatio(String dna){
        int count = 0;
        int l = dna.length();        
        for (int i = 0; i < l; i++){
            
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                count++;
            }            
        }   
        //System.out.println(count);
        return (double) count/l;
    }
    public int countCTG(String dna){
        int count = 0;
        //start index = 0
        int currIdx = 0;
        while (true){            
            currIdx = dna.indexOf("CTG",currIdx);
            if (currIdx == -1){
                return count;
            }
            count++;
            currIdx = currIdx + 3;
        }
    }
    public void testCountCTG(){
        String dna = "AATGCTAACTAGCTGCTGACTAAT";
        System.out.println(countCTG(dna));
    }
    public void testCGRatio(){
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println(cgRatio(dna));
    }

}
