package com.example.admin.accessibilityservicetest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.accessibilityservicetest.R;

/**
 * 查看更多view
 * Created by liukan on 2018/4/11.
 */
public class FindMoreView extends View {
    private Paint paint = new Paint();
    private int moveDistance = 0;

    public FindMoreView(Context context) {
        super(context);
    }

    public FindMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        setWillNotDraw(false);
    }

    public void setMoveDistance(int moveDistance) {
        this.moveDistance = moveDistance;
    }

    private void initPaint() {
        paint.setAntiAlias(true);   //设置画笔为无锯齿   设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        paint.setColor(getResources().getColor(R.color.theme_channel_find_more_bg));
        paint.setStrokeWidth((float) 3.0);              //线宽   当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度
        paint.setStyle(Paint.Style.FILL);                   //空心效果   为FILL，FILL_OR_STROKE，或STROKE
        paint.setDither(true);   //设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setStrokeCap(Paint.Cap.ROUND);   //当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式Cap.ROUND,或方形样式Cap.SQUARE
        paint.setStrokeJoin(Paint.Join.ROUND);   //设置绘制时各图形的结合方式，如平滑效果等
    }

    public void setColor(int color){
        paint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        path.moveTo(getLeft() + moveDistance, getTop() + moveDistance);
        path.quadTo(getLeft() - moveDistance, (getTop() + getBottom()) / 2, getLeft() + moveDistance, getBottom() - moveDistance);
        path.lineTo(getRight(), getBottom() - moveDistance);
        path.lineTo(getRight(), getTop() + moveDistance);
        path.close();
        canvas.drawPath(path, paint);

    }
}
