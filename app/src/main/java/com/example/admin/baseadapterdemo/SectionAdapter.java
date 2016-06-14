package com.example.admin.baseadapterdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2016/6/14.
 */
public abstract class SectionAdapter<T> extends MultiItemCommonAdapter<T> {

    private SectionSupport mSectionSuppert;
    private static final int TYPE_SECTION = 0;
    private LinkedHashMap<String,Integer> mSections;
    private MultiItemTypeSupport<T> headerItemTypeSupport = new MultiItemTypeSupport<T>() {
        @Override
        public int getLayoutId(int itemType) {
            if (itemType == TYPE_SECTION)
                return mSectionSuppert.sectionHeaderLayoutId();
            else
            return mLayoutId;
        }

        @Override
        public int getItemViewType(int position, T t) {
            return mSections.values().contains(position)?TYPE_SECTION:1;
        }
    };


    @Override
    public int getItemViewType(int position) {
        return multiItemTypeSupport.getItemViewType(position,null);
    }

    final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver(){
        @Override
        public void onChanged() {
            super.onChanged();
            findSections();
        }
    };


    public SectionAdapter(Context context,int layoutId,List<T> datas,SectionSupport sectionSupport){

        super(context,datas,null);
        mLayoutId = layoutId;
        this.multiItemTypeSupport = headerItemTypeSupport;
        this.mSectionSuppert = sectionSupport;
        mSections = new LinkedHashMap<>();
        findSections();
        registerAdapterDataObserver(observer);
    }

//    protected boolean isEnable(int viewType){
//        if (viewType == TYPE_SECTION) return false;
//    }


    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(observer);
    }

    public void findSections(){
        int n =mDatas.size();
        int nSection = 0;
        mSections.clear();

        for (int i = 0;i<n;i++){
            String sectionName = mSectionSuppert.getTitle(mDatas.get(i));
            if (!mSections.containsKey(sectionName)){
                mSections.put(sectionName,i+nSection);
                nSection++;
            }
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + mSections.size();
    }

    public int getIndexForPosition(int position){
        int nSetions = 0;
        Set<Map.Entry<String,Integer>> entrySet = mSections.entrySet();
        for (Map.Entry<String,Integer> entry:entrySet) {
            if (entry.getValue()<position){
                nSetions++;
            }
        }

        return position - nSetions;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        position = getIndexForPosition(position);
        if (holder.getItemViewType() == TYPE_SECTION){
            holder.setText(mSectionSuppert.sectionTitleTextViewId(),mSectionSuppert.getTitle(mDatas.get(position)));
        }
    }
}
