
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part1 {
    public String findSimpleGene(String dna){
        //start codon with ATG
        int startIdx = dna.indexOf("ATG");
        if (startIdx == -1){
            //no ATD
            return "";
        }
        int stopIdx = dna.indexOf("TAA",startIdx+3);
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
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        String result = "";
        result = findSimpleGene(dna);
        System.out.println(result);
    }
    
}
