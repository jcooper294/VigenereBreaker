/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String fileName){
        
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        
        ArrayList<Movie> movies = new ArrayList<Movie>();
        
            for(CSVRecord record:parser){
            
                Movie movie = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));
                movies.add(movie);
            }
               
        return movies;
    }
    
     public ArrayList<Rater> loadRaters(String fileName){
        
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
                
        ArrayList<Rater> raters = new ArrayList<Rater>();
        int indexOfRater = 0;
        int exists = 0;
        
            for(CSVRecord record:parser){
                            
               Rating rating = new Rating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                
               for(int i=0;i<raters.size();i++){
                    if (raters.get(i).getID().equals(record.get("rater_id"))){
                    indexOfRater = i;
                    exists = 1;
                    }
                }      
                
                    if (exists == 0)   {
                                              
                        Rater rater = new EfficientRater(record.get("rater_id"));
                        rater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                        raters.add(rater);
                        
                        
                    }
                    else{
                                                  
                        
                        Rater rater = raters.get(indexOfRater);
                        rater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                        
                    }
                                   
                    exists = 0;

                }
                return raters;
           }
  

    public void testLoadMovies(){
        
        String fileName = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<String> directorWithMaxMovies = new ArrayList<String>();
        HashMap<String,Integer> dCount = new HashMap<String,Integer>();
        
        int comedies = 0;
        int overTwoHours = 0;
        int maxMoviesDirected = 0;
        
        movies = loadMovies(fileName);
        
        for(Movie m:movies){
            
           // System.out.println(m);        
        
            if(m.getGenres().indexOf("Comedy") != -1)
            comedies+=1;
            
            if(m.getMinutes() > 150)
            overTwoHours+=1;
            
            String[] directors = m.getDirector().split(",");
            
                    
            for(int i=0;i<directors.length;i++){
                
                if(!dCount.containsKey(directors[i]))
                    dCount.put(directors[i],1);
                else
                    dCount.put(directors[i],dCount.get(directors[i])+1);                
                
            }
            
              
    
    
        }
  
        for(String s:dCount.keySet()){
                if(dCount.get(s)>=maxMoviesDirected){
                    directorWithMaxMovies.add(s);
                    maxMoviesDirected = dCount.get(s);                    
                    
                }
                        
        }
  
  
    System.out.println("Comedies: " + comedies);
    System.out.println("Movies over 150 Minutes: " + overTwoHours);
    System.out.print("Most movies directed: " + maxMoviesDirected + " by " + directorWithMaxMovies);
}

    public void testLoadRaters(){
        
        String fileName = "data/ratings.csv";
        ArrayList<Rater> raters = new ArrayList<Rater>();
        
        raters = loadRaters(fileName);
        
        System.out.println("Total Raters: " + raters.size());
        
     /*   for(Rater r:raters){
            
            ArrayList<String> ratings = r.getItemsRated();
            System.out.println("Rater: " + r.getID());
            System.out.println("Ratings: " + r.numRatings());
            
        }
        
        for(Rater r:raters){
            
            ArrayList<String> ratings = r.getItemsRated();
            System.out.println("Rater: " + r.getID());
            
            for(String s:ratings){
            System.out.println("Movie ID: " + s + " Rating: " + r.getRating(s));
            }
            
        }*/
        
        int getRater = 193;
        int mostRatings = 0;
        int maxRatings = 0;
        ArrayList<String> mostRaters = new ArrayList<String>();
        HashMap<String,Integer> ratings = new HashMap<String,Integer>();
        ArrayList<String> itemsRated = new ArrayList<String>();
        ArrayList<String> mostRated = new ArrayList<String>();
        
        for(Rater r:raters){
            
            if (Integer.parseInt(r.getID()) == getRater){
                
                System.out.println("RaterID: " + getRater + " has " + r.numRatings() + " ratings.");
            }
            
            if (r.numRatings()>mostRatings)
            mostRatings = r.numRatings();
            
            itemsRated=r.getItemsRated();
            
            for(int i = 0;i<itemsRated.size();i++){
                
                if(!ratings.containsKey(itemsRated.get(i))){
                    ratings.put(itemsRated.get(i),1);
                }
                else
                    ratings.put(itemsRated.get(i),ratings.get(itemsRated.get(i))+1);
                
            }
            
        }
        
        for(Rater r:raters){
                   
            
            if (r.numRatings()==mostRatings)
            mostRaters.add(r.getID());
            
        }
        
        System.out.println(mostRaters.size() + " rater have/has the most ratings: ");
        
        for(int i = 0;i<mostRaters.size();i++){
            
            System.out.println(mostRaters.get(i));
            
        }
        
        for(String s:ratings.keySet()){
            
            if(ratings.get(s)>= mostRatings){
                mostRatings = ratings.get(s);
                mostRated.add(s);
            }
            
        }
        
        System.out.println("The most rated movies with " + mostRatings + " are: ");
        
        for(int i=0;i<mostRated.size();i++){
            
            System.out.println(mostRated.get(i));
          
            
        }
        
        for(String s:ratings.keySet()){
            
            if(s.equals("1798709"))
            System.out.println("# of Ratings for " + s + ": " + ratings.get(s));
            
        }
        
        System.out.println("Number of movies rated: " + ratings.size());
        
        
        
        
    }

}
