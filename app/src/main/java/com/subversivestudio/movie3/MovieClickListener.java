package com.subversivestudio.movie3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by geord_000 on 19/04/2016.
 */
public class MovieClickListener implements RecyclerView.OnItemTouchListener {

    public interface OnItemClickListener{

        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }



    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    public MovieClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener){
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
            @Override
            public void onLongPress(MotionEvent e){
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && mListener != null) {
                    mListener.onItemLongClick(childView, recyclerView.getChildPosition(childView));
                }
            }
        });
    }
    //@Override
    public boolean OnInterceptTouchEvent(RecyclerView view, MotionEvent e){
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if(childView != null && mListener != null && mGestureDetector.onTouchEvent(e)){
            mListener.onItemLongClick(childView, view.getChildPosition(childView));
        }
        return false;
    }

    public void OnTouchEvent(RecyclerView view, MotionEvent motionEvent){

    }
}
