
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate(){
        
        ArrayList<String> moviesToRate = new ArrayList<String>();
        
        moviesToRate.add("816692");
        moviesToRate.add("1454468");
        moviesToRate.add("1345836");
        moviesToRate.add("1440728");
        moviesToRate.add("1229238");
        moviesToRate.add("1650554");
        moviesToRate.add("1542344");
        moviesToRate.add("482571");
        moviesToRate.add("172495");
        moviesToRate.add("1568346");
        moviesToRate.add("167260");
        moviesToRate.add("217505");
        moviesToRate.add("103060");
        moviesToRate.add("1951266");
        moviesToRate.add("1119646");       
        
        return moviesToRate;
    }
    
    public void printRecommendationsFor(String webRaterID){
        
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID,1,1);
        
        System.out.println("Movies Recommended for you: ");
        
        for(int i = 0;i<10;i++){
            System.out.println(ratings.get(i));
        }
                
        
    }

}
