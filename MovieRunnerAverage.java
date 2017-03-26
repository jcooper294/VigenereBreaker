
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public MovieRunnerAverage(){
    }
    
    public void printAverageRatings(){

        int numOfMovies;
        int numOfRaters;
        
        
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");

        numOfMovies = sr.getMovieSize();
        numOfRaters = sr.getRaterSize();
        
        System.out.println("Number of Movies: " + numOfMovies);
        System.out.println("Number of Raters: " + numOfRaters);

        ArrayList<Rating> ratings = sr.getAverageRatings(12);
        
        Collections.sort(ratings);
        
        for(int i = 0;i<ratings.size();i++){
                           
            System.out.println(ratings.get(i).getValue() + " " + sr.getTitle(ratings.get(i).getItem()));
            
        }
        
    }
    
    public void getAverageRatingOneMovie(){
        
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        ArrayList<Rating> ratings = sr.getAverageRatings(0);
        
        String title = "Vacation";
        
        for(int i = 0;i<ratings.size();i++){
            
            if (sr.getTitle(ratings.get(i).getItem()).equals(title))
            System.out.println(ratings.get(i).getValue() + " " + sr.getTitle(ratings.get(i).getItem()));
            
        } 
        
        
        
    }

}
