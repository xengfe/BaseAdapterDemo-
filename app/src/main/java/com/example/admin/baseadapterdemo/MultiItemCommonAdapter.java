package com.example.admin.baseadapterdemo;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by admin on 2016/6/14.
 */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    protected MultiItemTypeSupport<T> multiItemTypeSupport;

    public MultiItemCommonAdapter(Context context,List<T> datas,MultiItemTypeSupport<T> multiItemTypeSupport)
    {
        super(context,-1,datas);
        this.multiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return multiItemTypeSupport.getItemViewType(position,mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = multiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.get(mContext,parent,layoutId);
        return holder;
    }
}
