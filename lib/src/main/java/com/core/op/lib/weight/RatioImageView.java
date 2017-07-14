package com.core.op.lib.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author: zjl
 * @Time: 2017/7/14 15:17
 * @Desc:
 */

public class RatioImageView extends android.support.v7.widget.AppCompatImageView {
    public RatioImageView(Context context) {
        this(context, null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
