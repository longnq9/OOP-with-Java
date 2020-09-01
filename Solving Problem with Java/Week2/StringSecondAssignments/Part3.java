
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part3 {
    public int findStopCodon(String dna, int startIdx, String stopCodon){
        int currIdx = dna.indexOf(stopCodon, startIdx);
        while (currIdx != -1){
            if ((currIdx - startIdx) % 3 == 0) {
                 return currIdx;       
            }
            else{
                currIdx = dna.indexOf(stopCodon, currIdx + 1);                  
            }
        }        
        return -1;
    }
    public String findGene(String dna, int where){
        int startIdx = dna.indexOf("ATG", where);
        if (startIdx == -1){
            return "";
        }
        int taaIdx = findStopCodon(dna, startIdx, "TAA");
        int tagIdx = findStopCodon(dna, startIdx, "TAG");
        int tgaIdx = findStopCodon(dna, startIdx, "TGA");
        int minIdx;
        //int minIdx = Math.min(taaIdx, Math.min(tagIdx, tgaIdx));
        if (taaIdx == -1 || (tgaIdx != -1 && tgaIdx < taaIdx)){
            minIdx = tgaIdx;
        }
        else
        {
            minIdx = taaIdx;
        }
        if (minIdx == -1 || (tagIdx != -1 && tagIdx < minIdx)){
            minIdx = tagIdx;
        }
        if (minIdx == dna.length()){
            return "";
        }
        return dna.substring(startIdx, minIdx + 3);
    }
    public void printAllGenes(String dna){        
        //set startIdx = 0
        int startIdx = 0;
        //Repeat the following steps
        while (true) {
            //find the next gene after startIdx
            String currGene = findGene(dna, startIdx);
            //If no gene was found, leave this loop
            if (currGene.isEmpty()){
                break;
            }            
            //Print that gene out
            System.out.println(currGene);
            //update startIdx
            startIdx = dna.indexOf(currGene, startIdx) + currGene.length();
        }        
    }
    public int countGenes(String dna){
        int countGenes = 0;
        int startIdx = 0;
        while (true) {
            String currGene = findGene(dna, startIdx);
            if (currGene.isEmpty()){
                break;
            }
            countGenes++;
            startIdx = dna.indexOf(currGene, startIdx) + currGene.length();
        }
        return countGenes;
    }
    public void testCountGenes(){
        String dna = "ACATGAGGTAGCAATGAGGGTCGAGTAACTGATGAGGTCGAGTCACTGAGGTT";
        System.out.println(countGenes(dna));
    }
}
