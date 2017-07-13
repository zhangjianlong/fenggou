package com.core.op.lib.weight.sortView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.core.op.Static;
import com.core.op.lib.R;

/**
 * @author: zjl
 * @Time: 2017/7/13 13:21
 * @Desc:
 */

public class SortItemView extends android.support.v7.widget.AppCompatTextView {

    private SortRes sortRes;
    private Drawable drawable = null;

    public SortItemView(Context context) {
        this(context, null);
    }

    public SortItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


    }

    public void init(SortRes sortRes) {
        this.sortRes = sortRes;
        setText(sortRes.getTextRes());
        setGravity(Gravity.CENTER);

        Drawable drawable = null;
        switch (sortRes.getSort()) {
            case SortRes.DEFAULT:
                break;

            case SortRes.AES:
                drawable = Static.CONTEXT.getResources().getDrawable(sortRes.getAesRes());
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                break;
            case SortRes.DES:
                drawable = Static.CONTEXT.getResources().getDrawable(sortRes.getDesRes());
//                setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                break;
        }


    }


    public void updateView() {
        switch (sortRes.getSort()) {
            case SortRes.AES:
                drawable = Static.CONTEXT.getResources().getDrawable(sortRes.getDesRes());
                sortRes.setSort(SortRes.DES);
                invalidate();
//                setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                break;
            case SortRes.DES:
                drawable = Static.CONTEXT.getResources().getDrawable(sortRes.getAesRes());
                sortRes.setSort(SortRes.AES);
                invalidate();
//                setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                break;
        }

    }


    public void updateNullDrawable() {
        drawable = null;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawable != null) {
            float totalWidth = getWidth();
            float totalHeight = getHeight();
            float textWidth = getPaint().measureText(getText().toString());
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();
            BitmapDrawable bd = (BitmapDrawable) drawable;
            Bitmap bitmap = bd.getBitmap();
            canvas.drawBitmap(bitmap, totalWidth / 2 + textWidth / 2 + 10, totalHeight / 2 - drawableHeight / 2, getPaint());

        }

    }
}
