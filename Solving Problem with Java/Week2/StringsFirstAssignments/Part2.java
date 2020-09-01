
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        boolean isUpper = false;        
        String dnaUC = dna.toUpperCase();
        if (dnaUC == dna){
            isUpper = true;            
        }
        
        String checkStartCondon = isUpper ? startCodon.toUpperCase() : startCodon.toLowerCase();
        String checkStopCondon = isUpper ? stopCodon.toUpperCase() : stopCodon.toLowerCase();

        //start codon with
        int startIdx = dna.indexOf(startCodon);        
        if (startIdx == -1){
            //no ATG
            return "";
        }
        int stopIdx = dna.indexOf(stopCodon,startIdx+3);
        if (stopIdx == -1){
            //no TAA
            return "";
        }
        if ((stopIdx - (startIdx+3)) % 3 == 0) {
            return dna.substring(startIdx, stopIdx + 3);
        }else 
        {
            return "";
        }
    }
    public void testing(){
        String dna = "aatgctagggtaatatggt";
        String startCodon = "atg";
        String stopCodon = "taa";
        String result = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(result);
    }
    
}