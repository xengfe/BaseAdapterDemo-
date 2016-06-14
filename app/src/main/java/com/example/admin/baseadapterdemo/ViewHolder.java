package com.example.admin.baseadapterdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 2016/6/14.
 */
public   class ViewHolder extends RecyclerView.ViewHolder
{

    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;


     public ViewHolder(Context context, View itemView, ViewGroup parent)
     {
         super(itemView);
         mContext = context;
         mConvertView = itemView;
         mViews = new SparseArray<>();
     }

    public static ViewHolder get(Context context,ViewGroup parent,int layoutId)
    {
        View itemView = LayoutInflater.from(context).inflate(layoutId,parent);
        ViewHolder holder = new ViewHolder(context,itemView,parent);
        return holder;
    }

    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }

        return (T)view;
    }


    public ViewHolder setText(int textId,String text)
    {
        TextView tv = getView(textId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId,int resId)
    {
        ImageView image = getView(viewId);
        image.setImageResource(resId);
        return this;
    }

    public ViewHolder setOnclickListener(int viewId,View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
