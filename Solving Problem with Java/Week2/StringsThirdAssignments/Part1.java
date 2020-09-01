
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;
public class Part1 {
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
    
    public StorageResource getAllGenes(String dna){        
        StorageResource geneList = new StorageResource();
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
            geneList.add(currGene);
            //Print that gene out
            //System.out.println(currGene);
            //update startIdx
            startIdx = dna.indexOf(currGene, startIdx) + currGene.length();
        }
        return geneList;
    }
    
    public void testFindStopCodon(){
        String dna = "ACATGAGGCCTCACTGAGTCACCCAGGTT";
        int startIdx = dna.indexOf("ATG");
        System.out.println(findStopCodon(dna,startIdx,"TGA"));        
    }
    public void testFindGene(){
        String dna = "AATGCTAACTAGCTGACTAAT";
        int startIdx = dna.indexOf("ATG");
        String gene = findGene(dna, startIdx);
        System.out.println(gene);
    }
    public void testPrintAllGenes(){      
        String dna = "ACATGAGGTAGCAATGAGGGTCGAGTAACTGATGAGGTCGAGTCACTGAGGTT";
        printAllGenes(dna);
    }
    public void testGetAllGenes(){
        FileResource f = new FileResource("brca1line.fa");
        String dna = (f.asString()).toUpperCase();
        //String dna = "ACATGAGGTAGCAATGAGGGTCGAGTAACTGATGAGGTCGAGTCACTGAGGTT";
        StorageResource genes = getAllGenes(dna);
        for (String s : genes.data()){
            System.out.println(s);
        }
    }
}
