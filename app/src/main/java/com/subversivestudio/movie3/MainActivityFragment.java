package com.subversivestudio.movie3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Context c;

    private static final String LOG_TAG = "MainActivityFragment";
    public static final String MOVIE_TRANSFER = "MOVIE TRANSFEWR";
    private List<Movie> movieList = new ArrayList<Movie>();
    private RecyclerView mRecyclerView;
    private MovieRecyclerViewAdaptor movieRecyclerViewAdaptor;

    public MainActivityFragment() {
        System.out.println("--------------------------------------");
    }



//    public MainActivityFragment(Context context) {
//        c = context;
//
//
//        System.out.println("--------------------------------------");
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("--------------------------------------");
        super.onCreate(savedInstanceState);
        System.out.println("--------------------------------------");
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        inflater.inflate(R.menu.menu_main, menu);
//    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.most_popular) {

            //updateWeather();
            System.out.println("you clicked most popular");
            Toast.makeText(getActivity().getApplicationContext(), "most popular", Toast.LENGTH_LONG).show();
            ProcessImages processImages = new ProcessImages(2);
            processImages.execute();

            return true;
        }

        if (id == R.id.highest_rated) {

            //updateWeather();
            System.out.println("you clicked highest rated");
            Toast.makeText(getActivity().getApplicationContext(), "highest rated", Toast.LENGTH_LONG).show();
            ProcessImages processImages = new ProcessImages(1);
            processImages.execute();

            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("0");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.listview_forecast);


        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieRecyclerViewAdaptor = new MovieRecyclerViewAdaptor(getActivity(), movieList);
        mRecyclerView.setAdapter(movieRecyclerViewAdaptor);



        System.out.println("1");
        ProcessImages processImages = new ProcessImages(2);
        processImages.execute();


        //ListView listView = (ListView)rootView.findViewById(R.id.listview_forecast)







        return rootView;
       // return mRecyclerView;

        //return inflater.inflate(R.layout.fragment_main, container, false);
    }



    public class ProcessImages extends GetMovieJsonData {

        public ProcessImages(int sortBy) {
            super(sortBy);
            System.out.println("main activity fragment procoss images");
        }

        public void execute(){
            super.execute();
            ProcessData processData = new ProcessData();
            System.out.println("6");
            processData.execute();
            System.out.println("7");
        }

        public class ProcessData extends DownloadJsonData {


            protected void onPostExecute(String webData){
                System.out.println("88888888888888888888888888888888888888888888888888888888888888888");

                //super.onPostExecute( webData );
                System.out.println("8");
                movieRecyclerViewAdaptor = new MovieRecyclerViewAdaptor(getActivity(), getMovieImages());

                mRecyclerView.setAdapter(movieRecyclerViewAdaptor);



            }

        }
   }


}
