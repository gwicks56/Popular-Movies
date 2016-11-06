package com.subversivestudio.movie3;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geord_000 on 17/04/2016.
 */
public class GetMovieJsonData extends GetRawData {

    private String LOG_TAG = GetMovieJsonData.class.getSimpleName();
    public String MOVIE_URL;
    public static final String MOVIE_URL_POPULAR = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=21198cd7fd810819782eadde323e817a";
    public static final String MOVIE_URL_RATED = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=21198cd7fd810819782eadde323e817a";




    private List<Movie> movies;

    public GetMovieJsonData(){

        super(null);
        movies = new ArrayList<Movie>();

    }

    public GetMovieJsonData(int sortBy){

        super(null);

        int sort = sortBy;

        if(sort == 1){
            MOVIE_URL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=21198cd7fd810819782eadde323e817a";
        }
        else if(sort == 2){
            MOVIE_URL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=21198cd7fd810819782eadde323e817a";
        }


        movies = new ArrayList<Movie>();

    }

    public List<Movie> getMovieImages() {
        return movies;
    }

    public void execute(){
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Executing download Json Data form URL: \n" + MOVIE_URL);
        downloadJsonData.execute();
    }

    public void processResult(){
        if(getMovieDownloadStatus() != DownloadStatus.OK){
            Log.e(LOG_TAG, "Error downloing raw file");
            return;
        }

        final String movieResults = "results";
        final String movieTitle = "title";
        final String movieOverview = "overview";
        final String posterPath = "poster_path";
        final String movieId = "id";
        final String voteCount = "vote_count";
        final String releaseDate = "release_date";
        final String voteAverage = "vote_average";

        try {

            JSONObject jsonData = new JSONObject(getMovieData());
            JSONArray resultsArray = jsonData.getJSONArray(movieResults);
            System.out.println("DOING THE JSON STUFF IN GETMOVIEJSDONDATA");
            for(int i = 0; i<resultsArray.length(); i++){

                JSONObject jsonMovie = resultsArray.getJSONObject(i);
                String title = jsonMovie.getString(movieTitle);
                int Id = jsonMovie.getInt(movieId);
                String overview = jsonMovie.getString(movieOverview);
                int votes = jsonMovie.getInt(voteCount);
                String poster = jsonMovie.getString(posterPath);
                String release = jsonMovie.getString(releaseDate);
                double average = jsonMovie.getDouble(voteAverage);
                System.out.println("*********************************" + average);
                String newAverage = String.valueOf(average);
                System.out.println("*********************************" + newAverage);


                Movie movieObject = new Movie(Id, title, overview, poster, votes, newAverage, release);
                this.movies.add(movieObject);
            }
            System.out.println("DOING THE JSON STUFF IN GETMOVIEJSDONDATA");

            for(Movie singleMovie: movies){
                Log.v(LOG_TAG, "Movies: \n" + singleMovie.toString());
            }

        }catch(JSONException jsone){
            jsone.printStackTrace();
            Log.e(LOG_TAG, "error processing json");
        }
    }

    public class DownloadJsonData extends DownloadRawData {

        protected void  onPostExecute(String webData){
            System.out.println("DownloadJsonData before on post execute___________________________________");
            super.onPostExecute(webData);
            System.out.println("DownloadJsonData before on post execute___________________________________");

            processResult();
            System.out.println("DownloadJsonData after [preccessresult___________________________________");

        }

        protected String doInBackground(String... params){
            String[] par = {MOVIE_URL.toString()};
            System.out.println("Before do in background ############################################################################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            return super.doInBackground(par);
           // return null;

        }
    }
}
