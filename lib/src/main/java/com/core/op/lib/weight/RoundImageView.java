package com.core.op.lib.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by acer on 2017/3/11.
 */

public class RoundImageView extends AppCompatImageView {
    private float radiusX;
    private float radiusY;

    public RoundImageView(Context context) {
        super(context);
        init();
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * @param rx x方向弧度
     * @param ry y方向弧度
     */
    public void setRadius(float rx, float ry) {
        this.radiusX = rx;
        this.radiusY = ry;
    }

    private void init() {
        radiusX = 20;
        radiusY = 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        RectF rectF = new RectF(rect);
        path.addRoundRect(rectF, radiusX, radiusY, Path.Direction.CCW);
        canvas.clipPath(path, Region.Op.REPLACE);//Op.REPLACE这个范围内的都将显示，超出的部分覆盖
        super.onDraw(canvas);
    }
}
