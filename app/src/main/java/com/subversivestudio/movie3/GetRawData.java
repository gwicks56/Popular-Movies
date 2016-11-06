package com.subversivestudio.movie3;

/**
 * Created by geord_000 on 17/04/2016.
 */
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by geord_000 on 17/04/2016.
 */

enum DownloadStatus { IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK }

public class GetRawData {

    private String LOG_TAG = GetRawData.class.getSimpleName();
    private String movieURL;
    private String movieData;
    private DownloadStatus movieDownloadStatus;

    public GetRawData(String movieURL) {
        this.movieURL = movieURL;
        this.movieDownloadStatus = DownloadStatus.IDLE;
    }

    public void reset(){
        this.movieDownloadStatus = DownloadStatus.IDLE;
        this.movieURL = null;
        this.movieData = null;
    }

    public String getMovieData() {
        return movieData;
    }

    public DownloadStatus getMovieDownloadStatus() {
        return movieDownloadStatus;
    }

    public void execute(){
        this.movieDownloadStatus = DownloadStatus.PROCESSING;
        DownloadRawData downloadRawData = new DownloadRawData();
        System.out.println("about to call download raw data");
        System.out.println("________________________________________________________");
        downloadRawData.execute(movieURL);
    }

    public class DownloadRawData extends AsyncTask<String, Void, String>{



        protected void onPostExecute(String webData) {
            System.out.println("CALLED ASYNCTASK______________*******************************************************************");
            movieData = webData;
            Log.v(LOG_TAG, "Data returned was: " + movieData);
            if(movieData == null){
                if(movieURL == null){
                    movieDownloadStatus = DownloadStatus.NOT_INITIALISED;
                }else{
                    movieDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
                }
            }else{
                movieDownloadStatus = DownloadStatus.OK;
            }
        }
        protected String doInBackground(String... params){
            System.out.println("GETRAWDATA DO IN BACKGROUND #################################################################################");
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if(params == null)
                return null;

            try{
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream == null){
                    return null;
                }

                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader( new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine()) != null){
                    buffer.append(line + "\n");
                }

                return buffer.toString();

            }catch(IOException e){
                Log.e(LOG_TAG, "Error", e);
                return null;
            }finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
                if(reader != null){
                    try{
                        reader.close();
                    }catch(final IOException e){
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
        }
    }
}
