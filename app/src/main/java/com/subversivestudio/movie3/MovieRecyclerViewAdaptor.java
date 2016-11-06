package com.subversivestudio.movie3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by geord_000 on 17/04/2016.
 */
public class MovieRecyclerViewAdaptor extends RecyclerView.Adapter<MovieImageViewHolder> {


    public static List<Movie> movieList;
    private Context movieContext;


    public MovieRecyclerViewAdaptor(Context context, List<Movie> movieList) {
        this.movieList = movieList;
        movieContext = context;
        //System.out.println("movie: " + movieList.get(0));
    }



    @Override
    public MovieImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, null);
        MovieImageViewHolder movieImageViewHolder = new MovieImageViewHolder(view);
        return movieImageViewHolder;



    }



    @Override
    public void onBindViewHolder(MovieImageViewHolder holder, int position) {
        final Movie movieItem = movieList.get(position);
        Picasso.with(movieContext).load("http://image.tmdb.org/t/p/w185//" + movieItem.getPoster_path()).into(holder.thumbnail);
        System.out.println("http://image.tmdb.org/t/p/w92/" + movieItem.getPoster_path());



    }

    public Movie getMovie(int position){
        return(null != movieList ? movieList.get(position) : null);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

//    @Override
//    public boolean areAllItemsEnabled() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled(int position) {
//        return false;
//    }
//
//    @Override
//    public void registerDataSetObserver(DataSetObserver observer) {
//
//    }
//
//    @Override
//    public void unregisterDataSetObserver(DataSetObserver observer) {
//
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 0;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }


}