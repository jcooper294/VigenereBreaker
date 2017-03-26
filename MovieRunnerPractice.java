
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerPractice {

    public void printAverageRatingsByGenre(){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        FourthRatings fr = new FourthRatings("ratings.csv");
        Filter f = new GenreFilter("Comedy");
        
        ratings = fr.getAverageRatingsByFilter(20,f);
        
        
        
        for(int i = 0;i<ratings.size();i++){
            
            System.out.println(ratings.get(i).getValue() + " " + MovieDatabase.getTitle(ratings.get(i).getItem()));
            System.out.println(MovieDatabase.getGenres(ratings.get(i).getItem()));
            
        }
             System.out.println("Number of Movies Found: " + ratings.size()); 
    }
    
    public void printAverageRatingsByYearAndGenre(){
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        FourthRatings fr = new FourthRatings("ratings.csv");
        
        
        Filter yf = new YearAfterFilter(1990);
        Filter gf = new GenreFilter("Drama");
        
        AllFilters af = new AllFilters();
        
        af.addFilter(yf);
        af.addFilter(gf);
        
        ratings = fr.getAverageRatingsByFilter(8,af);
        
        
        
        for(int i = 0;i<ratings.size();i++){
            
            System.out.println(ratings.get(i).getValue() + " " + MovieDatabase.getYear(ratings.get(i).getItem()) + " " + MovieDatabase.getTitle(ratings.get(i).getItem()));
            System.out.println(MovieDatabase.getGenres(ratings.get(i).getItem()));
            
        }
            System.out.println("Number of Movies Found: " + ratings.size());  
    }
    
    public void printSimilarRatings(){
        
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        ArrayList<Rating> ratings = fr.getSimilarRatings("71",20,5);
        
        for(Rating r:ratings){
                
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
        
                
    }
    
     public void printSimilarRatingsByGenre(){
        
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        Filter gf = new GenreFilter("Mystery");
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("964",20,5,gf);
        
        for(Rating r:ratings){
                
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
                        
    }
    
    public void printSimilarRatingsByDirector(){
        
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        Filter df = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("120",10,2,df);
        
        for(Rating r:ratings){
                
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
             System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
                        
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        Filter gf = new GenreFilter("Drama");
        Filter mf = new MinutesFilter(80,160);
        
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("168",10,3,af);
        
        for(Rating r:ratings){
                
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " Time: " + MovieDatabase.getMinutes(r.getItem())+ " " + r.getValue());
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
                        
    }
    
     public void printSimilarRatingsByYearAfterAndMinutes(){
        
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        Filter yf = new YearAfterFilter(1975);
        Filter mf = new MinutesFilter(70,200);
        
        AllFilters af = new AllFilters();
        af.addFilter(yf);
        af.addFilter(mf);
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314",10,5,af);
        
        for(Rating r:ratings){
                
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " Year: " + MovieDatabase.getYear(r.getItem()) + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " +r.getValue());
        }
                        
    }
    
}
