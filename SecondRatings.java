
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile,String ratingsFile){

        FirstRatings fr = new FirstRatings();
        
        myMovies = fr.loadMovies(movieFile);
        myRaters = fr.loadRaters(ratingsFile);

    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        
        
        for(int i = 0;i<myMovies.size();i++){
        
            String id = myMovies.get(i).getID();
            double rating = getAverageByID(id,minimalRaters);
            
            if(rating>0.0){
            Rating myRating = new Rating(id,rating);
            ratings.add(myRating);
        }
        }
        
        
        return ratings;
    }
    
    
    private double getAverageByID(String id,int minimalRaters){
        
        HashMap<String,Integer> numberOfRatings = new HashMap<String,Integer>();
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
    
    public String getTitle(String id){
        
        for(int i=0;i<myMovies.size();i++){
            if(myMovies.get(i).getID().equals(id))
            return myMovies.get(i).getTitle();
        }
               
        return "ID Not Found";
    }
    
    public String getID(String title){
        
         for(int i=0;i<myMovies.size();i++){
            if(myMovies.get(i).getTitle().equals(title))
            return myMovies.get(i).getID();
        }    
        
        return "NO SUCH TITLE";
    }
    
    
}