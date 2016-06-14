package com.example.admin.baseadapterdemo;

/**
 * Created by admin on 2016/6/14.
 */
public interface SectionSupport<T> {

    public int sectionHeaderLayoutId();
    public int sectionTitleTextViewId();
    public String getTitle(T t);
}
