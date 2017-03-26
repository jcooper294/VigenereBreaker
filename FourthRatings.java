
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
        
    public FourthRatings() {
         this("ratings.csv");
    }
    
    public FourthRatings(String ratingsFile){

        FirstRatings fr = new FirstRatings();
               
        RaterDatabase.initialize(ratingsFile);

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
               
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        
        for(int i = 0;i<RaterDatabase.size();i++){
            
            Rater rater = raters.get(i);
            
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
        
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        
        for(int i = 0;i<movies.size();i++){
            
            for (int j=0;j<RaterDatabase.size();j++){
            
                 Rater rater = raters.get(j);
            
                                
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
            rating = 0;                   
        }
        
        
        return allRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter filter){
        
        ArrayList<Rating> ratings = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        
        int minRatings = 0;
        double movieRating = 0.0;
        
        for(String movieID:MovieDatabase.filterBy(filter)){
            
                       
            movieRating = 0.0;
            minRatings = 0;
                        
            for(int i = 0;i<numSimilarRaters;i++){
                
                String raterID = ratings.get(i).getItem();
                Rater rater = RaterDatabase.getRater(raterID);
                ArrayList<String> itemsRated = rater.getItemsRated();
                
                if(itemsRated.contains(movieID))
                minRatings +=1;
                
            }
            
            if(minRatings>=minimalRaters){
            
            for(int i = 0;i<numSimilarRaters;i++){
                
                Rating r = ratings.get(i);
                Rater rater = RaterDatabase.getRater(r.getItem());
                if(rater.getRating(movieID)!=-1)
                movieRating += (rater.getRating(movieID)*r.getValue());
                
            }
            
            movieRating=movieRating/minRatings;
            Rating wRating = new Rating(movieID,movieRating);
            ret.add(wRating);   
        }
            
            
        }
        
        Collections.sort(ret);             
        
        return ret;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
                    
        return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,new TrueFilter());
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r: RaterDatabase.getRaters()){
            
            if (!r.getID().equals(me.getID())){
                Rating rating = new Rating(r.getID(),dotProduct(me,r));
                
                if(dotProduct(me,r)>0.0)
                list.add(rating);
                
            }
                       
        }
        
        Collections.sort(list,Collections.reverseOrder());
                               
        return list;      
        
    }
    
    
    private double dotProduct(Rater me,Rater r){
        
        double dotProduct = 0.0;
        
        ArrayList<String> meRatings = me.getItemsRated();
        ArrayList<String> rRatings = r.getItemsRated();
        ArrayList<String> list = MovieDatabase.filterBy(new TrueFilter());
        
        for(String s:list){
        
            if(meRatings.contains(s) && rRatings.contains(s)){
                
                dotProduct += ((me.getRating(s)-5)*(r.getRating(s)-5));
                
            }
        }
                       
        return dotProduct;
    }
        
    
}