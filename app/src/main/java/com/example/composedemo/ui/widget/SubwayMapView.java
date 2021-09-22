package com.example.composedemo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SubwayMapView extends View implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private Paint paint;
    private float scale = 1f;
    private float dx = 0 ,dy = 0;

    private float mFirstX,mFirstY,mSecondX,mSecondY;
    private int mOldCounts;

    private GestureDetector mGestureDetector;

    public SubwayMapView(Context context) {
        super(context);
        paint = new Paint();
        mGestureDetector = new GestureDetector(this);
    }
    public SubwayMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        mGestureDetector = new GestureDetector(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        canvas.save();

        canvas.scale(scale, scale);
        canvas.translate(dx, dy);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200, 200, paint);

        canvas.restore();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mOldCounts = 1;
                mFirstX = event.getX();
                mFirstY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE: {
                float fFirstX = event.getX();
                float fFirstY = event.getY();
                int nCounts = event.getPointerCount();
                if (1 == nCounts) {
                    mOldCounts = 1;
                } else if (1 == mOldCounts) {
                    mSecondX = event.getX(event.getPointerId(nCounts - 1));
                    mSecondY = event.getY(event.getPointerId(nCounts - 1));
                    mOldCounts = nCounts;
                } else {
                    float fSecondX = event
                            .getX(event.getPointerId(nCounts - 1));
                    float fSecondY = event
                            .getY(event.getPointerId(nCounts - 1));

                    double nLengthOld = getLength(mFirstX, mFirstY, mSecondX,
                            mSecondY);
                    double nLengthNow = getLength(fFirstX, fFirstY, fSecondX,
                            fSecondY);

                    float d = (float) ((nLengthNow - nLengthOld) / v.getWidth());

                    scale += d;

                    if(scale>3){
                        scale=3f;
                    }
                    if(scale<0.5){
                        scale=0.5f;
                    }
                    mSecondX = fSecondX;
                    mSecondY = fSecondY;
                }
                mFirstX = fFirstX;
                mFirstY = fFirstY;
                break;
            }
        }

        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        this.dx = this.dx - (e1.getX()-e2.getX())/50;
        this.dy = this.dy - (e1.getY()-e2.getY())/50;
        invalidate();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    private double getLength(float x1, float y1, float x2, float y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}