
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile){

        FirstRatings fr = new FirstRatings();
               
        myRaters = fr.loadRaters(ratingsFile);

    }
    
        
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(int i = 0;i<movies.size();i++){
        
            String id = movies.get(i);
            double rating = getAverageByID(id,minimalRaters);
            
            if(rating>0.0){
            Rating myRating = new Rating(id,rating);
            ratings.add(myRating);
        }
        }
        
        
        return ratings;
    }
    
    
    private double getAverageByID(String id,int minimalRaters){
        
        //HashMap<String,Integer> numberOfRatings = new HashMap<String,Integer>();
        double rating = 0.0;
        int ratings = 0;
               
        
        for(int i = 0;i<myRaters.size();i++){
            
            Rater rater = myRaters.get(i);
            
            if(rater.getRating(id)>=0){
                ratings+=1;
                rating += rater.getRating(id);
            }
        }
        
        if (ratings>=minimalRaters)
        return rating/ratings;
        
        return 0.0;
        
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> allRatings = new ArrayList<Rating>();
        
        double rating = 0.0;
        int ratings = 0;
               
        
        for(int i = 0;i<movies.size();i++){
            
            for (int j=0;j<myRaters.size();j++){
            
                 Rater rater = myRaters.get(j);
            
                                
                 if(rater.getRating(movies.get(i))>=0){
                     ratings+=1;
                     rating += rater.getRating(movies.get(i));
                 }
            }
            
            if(ratings>=minimalRaters){
                Rating movieRating = new Rating(movies.get(i),rating/ratings);
                allRatings.add(movieRating);
            }     
            
            ratings = 0;
            
        }
        return allRatings;
    }
        
    
}