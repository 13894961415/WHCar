package com.example.whcar.Home.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by liu on 2018/7/16.
 */


public class ScrollViewContainListView extends ListView {

    public ScrollViewContainListView(Context context) {
        super(context);
    }

    public ScrollViewContainListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewContainListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}