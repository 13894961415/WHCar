package com.example.whcar.Home.utils;
import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(@NonNull Context context) {
        super(context);
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setPageTransformer(true,new VerticalPageTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(MotionEvent.obtain(ev)));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(swapTouchEvent(MotionEvent.obtain(ev)));
    }

    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();
        event.setLocation((event.getY() / height) * width, ((event.getX() / width) * height));
        return event;
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(@NonNull View view, float position) {
            if (position<-1){
                view.setAlpha(0);
            }else if (position <=1){
                view.setAlpha(1);
                view.setTranslationX(view.getWidth()*-position);
                float yPosition = position* view.getHeight();
                view.setTranslationY(yPosition);
            }else {
                view.setAlpha(0);
            }
        }
    }
}
