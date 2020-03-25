package com.example.blood.ecom;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import static java.lang.Math.min;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;
    @Override
    public void transformPage(@NonNull View page, float pos) {

        final float height = page.getHeight();
        final float width = page.getWidth();
        final float scale = min( pos < 0 ? 1f : Math.abs(1f - pos), 1f );

        page.setScaleX( scale );
        page.setScaleY( scale );
        page.setPivotX( width * 0.5f );
        page.setPivotY( height * 0.5f );
        page.setTranslationX( pos < 0 ? width * pos : -width * pos * 0.25f );

    }
}
