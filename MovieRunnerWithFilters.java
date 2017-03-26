
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings(){

        int numOfMovies;
        int numOfRaters;
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        
        numOfRaters = tr.getRaterSize();
        
        System.out.println("Number of Raters: " + numOfRaters);
       
        
        System.out.println("Number of Movies: " + MovieDatabase.size());
        System.out.println("Number of Raters: " + numOfRaters);
        
        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        
        System.out.println(ratings.size() + " movies found.");
        
        Collections.sort(ratings);
        
        
        
        for(int i = 0;i<ratings.size();i++){
                           
            System.out.println(ratings.get(i).getValue() + " " + MovieDatabase.getTitle(ratings.get(i).getItem()));
            
        }
        
    }
    
    public void printAverageRatingsByYear(){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        YearAfterFilter f = new YearAfterFilter(2000);
        
        ratings = tr.getAverageRatingsByFilter(20,f);
        
        
        
        for(int i = 0;i<ratings.size();i++){
            
            System.out.println(ratings.get(i).getValue() + " " + MovieDatabase.getTitle(ratings.get(i).getItem()) + " " + MovieDatabase.getYear(ratings.get(i).getItem()));
            
        }
          
        System.out.println("Number of Movies Found: " + ratings.size());
    }
    
    public void printAverageRatingsByGenre(){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        Filter f = new GenreFilter("Comedy");
        
        ratings = tr.getAverageRatingsByFilter(20,f);
        
        
        
        for(int i = 0;i<ratings.size();i++){
            
            System.out.println(ratings.get(i).getValue() + " " + MovieDatabase.getTitle(ratings.get(i).getItem()));
            System.out.println(MovieDatabase.getGenres(ratings.get(i).getItem()));
            
        }
             System.out.println("Number of Movies Found: " + ratings.size()); 
    }
    
     public void printAverageRatingsByMinutes(){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        Filter f = new MinutesFilter(105,135);
        
        ratings = tr.getAverageRatingsByFilter(5,f);
        
        
        
        for(int i = 0;i<ratings.size();i++){
            
            System.out.println(ratings.get(i).getValue() + " Time: " + MovieDatabase.getMinutes(ratings.get(i).getItem()) + " " +MovieDatabase.getTitle(ratings.get(i).getItem()));
            
            
        }
           System.out.println("Number of Movies Found: " + ratings.size());   
    }
    
     public void printAverageRatingsByDirectors(){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        ratings = tr.getAverageRatingsByFilter(4,f);
        
        
        
        for(int i = 0;i<ratings.size();i++){
            
            System.out.println(ratings.get(i).getValue() + " " + MovieDatabase.getTitle(ratings.get(i).getItem()));
            System.out.println(MovieDatabase.getDirector(ratings.get(i).getItem()));
            
        }
             System.out.println("Number of Movies Found: " + ratings.size()); 
    }
    
    public void printAverageRatingsByYearAndGenre(){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        
        
        Filter yf = new YearAfterFilter(1990);
        Filter gf = new GenreFilter("Drama");
        
        AllFilters af = new AllFilters();
        
        af.addFilter(yf);
        af.addFilter(gf);
        
        ratings = tr.getAverageRatingsByFilter(8,af);
        
        
        
        for(int i = 0;i<ratings.size();i++){
            
            System.out.println(ratings.get(i).getValue() + " " + MovieDatabase.getYear(ratings.get(i).getItem()) + " " + MovieDatabase.getTitle(ratings.get(i).getItem()));
            System.out.println(MovieDatabase.getGenres(ratings.get(i).getItem()));
            
        }
            System.out.println("Number of Movies Found: " + ratings.size());  
    }
    
     public void printAverageRatingsByDirectorsAndMinutes(){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        
        
        Filter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter mf = new MinutesFilter(90,180);
        
        AllFilters af = new AllFilters();
        
        af.addFilter(df);
        af.addFilter(mf);
        
        ratings = tr.getAverageRatingsByFilter(3,af);
        
        
        
        for(int i = 0;i<ratings.size();i++){
            
            System.out.println(ratings.get(i).getValue() + " Time: " + MovieDatabase.getMinutes(ratings.get(i).getItem()) + " " +MovieDatabase.getTitle(ratings.get(i).getItem()));
            System.out.println(MovieDatabase.getDirector(ratings.get(i).getItem()));
            
        }
        System.out.println("Number of Movies Found: " + ratings.size());      
    }
    
     
    

}
