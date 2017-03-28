
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class tester {

    public void testCaesarCipher(){
        
    CaesarCipher cc = new CaesarCipher(5);       
    
    StringBuilder sb = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    char letter;
    
    FileResource fr = new FileResource();
    String allContent = fr.asString();
    
        for(int i = 0; i< allContent.length()-1;i++){
            
            sb.append(cc.encrypt(allContent.substring(i,i+1)));
               
        }
        
        System.out.print(sb);
        
        for(int i = 0; i< allContent.length()-1;i++){
            
            sb2.append(cc.decrypt(sb.substring(i,i+1)));
               
        }
        
        System.out.print(sb2);
    }
    
    public void testCaesarBreaker(){
        
    CaesarCracker cc = new CaesarCracker();
    CaesarCracker ccPort = new CaesarCracker('a');
     
    
    FileResource fr = new FileResource();
    String allContent = fr.asString();
    
   
   // System.out.print(cc.decrypt(allContent));
     System.out.print(ccPort.decrypt(allContent));    
        
    }
        
    public void testVigenereCipher(){
        
        int[] key = {17,14,12,4};        
        VigenereCipher vc = new VigenereCipher(key);
                       
        System.out.print(vc.decrypt("Tcmp-pxety mj nikhqv htee mrfhtii tyv"));
            
        
    }
    
    
    
    
}
