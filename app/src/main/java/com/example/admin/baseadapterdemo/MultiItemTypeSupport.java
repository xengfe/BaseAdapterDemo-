package com.example.admin.baseadapterdemo;

/**
 * Created by admin on 2016/6/14.
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int itemType);
    int getItemViewType(int position,T t);
}
