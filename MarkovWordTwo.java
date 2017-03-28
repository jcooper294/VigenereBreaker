
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key = myText[index];
		String key2 = myText[index+1];
		sb.append(key);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		
				
		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key,key2);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
							        
			key = key2;
			key2 = next;
			
	     
			
		}
		
		return sb.toString().trim();
	}
	
	private int indexOf(String[] words, String target, String target2,int start){
	    
	    for(int k=start;k<words.length;k++){
	        if (words[k].equals(target) && words[k+1].equals(target2))
	        return k;	        
	       }
	    	    
	return -1;
}
	
	private ArrayList<String> getFollows(String key,String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
	    
	    int pos = 0;
	    while (pos <myText.length){
	        int start = indexOf(myText,key,key2,pos);
	       	        
	        if (start == -1){
	            break;
	           }
	           
	        if (start + key.length() >= myText.length-2){
	            break;
	           }
	        
	        String next = myText[start+2];
	        follows.add(next);
	        pos = start + 1;//key.length();
	       }
	    
	    
	    return follows;
    }

}
