import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    public String oLanguage = "";
    public int myKey = 0;
    
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
       
        StringBuilder thisSlice = new StringBuilder();
        
        for(int i = whichSlice;i<message.length();i+=totalSlices){
            
        thisSlice.append(message.substring(i,i+1));   
            
        }
                
        return thisSlice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String slice;
        CaesarCracker cc = new CaesarCracker();
        
        for (int i = 0; i<klength;i++){
            
            slice = sliceString(encrypted,i,klength);
            key[i] = cc.getKey(slice);
            
        }
                        
        return key;
    }

    public void breakVigenere () {
           
        
        FileResource fr = new FileResource();
        String allContent = fr.asString();
        String decrypted;
        char mostCommon;
        HashSet<String> dictionary = new HashSet<String>();
        HashMap<String,HashSet<String>> langs = new HashMap<String,HashSet<String>>();
        
        DirectoryResource dr= new DirectoryResource();
        
        System.out.println("Loading dictionaries....");
        
        for(File f:dr.selectedFiles()){
        
            FileResource dictionaryFile = new FileResource(f);   
            dictionary = readDictionary(dictionaryFile);
            langs.put(f.getName(),dictionary);
                 
        }
        
             
        System.out.println("Attempting to decrypt file...");
               
        decrypted = breakForAllLangs(allContent,langs);
        
        //System.out.print(decrypted);
        System.out.println("Original Language is: " + oLanguage);      
        
        System.out.println("Key Length: " + myKey);
    }
    
    public String breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        
        HashSet<String> dictionary = new HashSet<String>();
        String[] allD = new String[15];
        int i =0;
        int wordCount = 0;
        int mostWords = 0;
        String decrypted = "";
        
        for(String s:languages.keySet()){
            
        dictionary = languages.get(s);      
        
        allD[i] = breakForLanguage(encrypted,dictionary); 
        wordCount = countWords(allD[i],dictionary);
            
               
            if (wordCount > mostWords){
                System.out.println("Counting words....");
                mostWords = wordCount;        
                decrypted = allD[i];
                oLanguage = s;
            }
        
        i++;
        }
           
        System.out.println("Decryption found!");
        
        return decrypted;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        
        HashSet<String> dictionary = new HashSet<String>();
        
        for(String s:fr.lines()){
            
            s=s.toLowerCase();
            dictionary.add(s);
            
        }
              
        return dictionary;
    }
    
    public int countWords(String message,HashSet<String> dictionary){
    
        int realWords = 0;    
        String[] output = message.split("\\W+");
    
        for(String s:output){
            if (dictionary.contains(s))       
                realWords+=1;
            
        }   
              
    return realWords;
   }
   
   public String breakForLanguage(String encrypted, HashSet<String> dictionary){
       
       String[] decrypted = new String[100];
       String bestDecrypted= "";
       
       int mostWords = 0;
       int[] wordCounts = new int[100];
       char mostCommon;
      
       mostCommon = mostCommonCharIn(dictionary);
       
       for (int i = 1;i<=100;i++){
       int[] key = new int[i];
       key = tryKeyLength(encrypted,i,mostCommon);
       
       if(i==6){
       System.out.print("Key: ");  
           for (int j=0;j<6;j++)
       System.out.print(key[j] + ",");
       }
       VigenereCipher vc = new VigenereCipher(key);
       decrypted[i-1] = vc.decrypt(encrypted);
       
           
       wordCounts[i-1] = countWords(decrypted[i-1],dictionary);
       
       }
       
       for (int i =0;i<100;i++){
           if (wordCounts[i] > mostWords){
               mostWords = wordCounts[i];
               bestDecrypted = decrypted[i];
               myKey = i+1;
               System.out.println("Most words is now: " + mostWords + " and the key length was " + (i +1));
           }
       }
       return bestDecrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        
        HashMap<String,Integer> charCounts= new HashMap<String,Integer>();
        int mostCommonNum = 0;
        String mostCommon ="";
        
        for (String s:dictionary){
            
            for(int i = 0;i<s.length();i++){
                
                String currChar = s.substring(i,i+1);
                
                if(!charCounts.containsKey(currChar))
                charCounts.put(currChar,1);
                else
                charCounts.put(currChar,charCounts.get(currChar)+1);
            }
        }
        
        for(String s:charCounts.keySet())
        if (charCounts.get(s) > mostCommonNum){
            mostCommonNum = charCounts.get(s);
            mostCommon = s;
        }
        
        return mostCommon.charAt(0);
    }
    
    
    
}
