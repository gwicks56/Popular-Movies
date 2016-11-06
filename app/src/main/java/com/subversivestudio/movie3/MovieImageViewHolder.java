package com.subversivestudio.movie3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by geord_000 on 17/04/2016.
 */
public class MovieImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    protected ImageView thumbnail;

    public static final String MOVIE_TRANSFER = "MOVIE TRANSFEWR";


    public MovieImageViewHolder(View view) {
        super(view);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
    }
    @Override
    public boolean onLongClick(View v) {
        //clickListener.onItemLongClick(getAdapterPosition(), v);
        int position = getLayoutPosition();
        System.out.println("LOOONNNNGGGGG CLICK CLICK CLICK");
        Context context = itemView.getContext();
        //Movie movie = new Movie
//        Intent intent = new Intent(context, MovieDetailActivity.class);
//        intent.putExtra(MOVIE_TRANSFER, MovieRecyclerViewAdaptor.movieList.get(position));
//        context.startActivity(intent);

        return false;
    }


    public void onClick(View v) {
        int position = getLayoutPosition();
        System.out.println("CLICK CLICK CLICK "  + position );
        Context context = itemView.getContext();
        //Movie movie = new Movie();
        Movie movie = MovieRecyclerViewAdaptor.movieList.get(position);
        System.out.println(movie.toString());
        System.out.println("the movie data is: " + MovieRecyclerViewAdaptor.movieList.get(position));
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("sample object", movie);
        context.startActivity(intent);

        //clickListener.onItemClick(getAdapterPosition(), v);
    }


//    public boolean onLongClick(View v) {
//        //clickListener.onItemLongClick(getAdapterPosition(), v);
//        System.out.println("LOOONNNNGGGGG CLICK CLICK CLICK");
//
//        return false;
//    }
}


