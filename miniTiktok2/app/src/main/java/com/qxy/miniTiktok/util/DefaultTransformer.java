package com.qxy.miniTiktok.util;


import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class DefaultTransformer implements ViewPager.PageTransformer {
    public static final String TAG = "simple";

    @Override
    public void transformPage(View view, float position) {

        float alpha = 0;
        if (0 <= position && position <= 1) {
            alpha = 1 - position;
        } else if (-1 < position && position < 0) {
            alpha = position + 1;
        }
        view.setAlpha(alpha);
        float transX = view.getWidth() * -position;
        view.setTranslationX(transX);
        float transY = position * view.getHeight();
        view.setTranslationY(transY);
    }
}
