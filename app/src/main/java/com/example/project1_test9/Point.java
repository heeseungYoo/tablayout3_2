package com.example.project1_test9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Point {
    float x;
    float y;
    int pValue;
    int color;
    int size;

    public Point(float x, float y, int value, int color, int size) {
        this.x = x;
        this.y = y;
        pValue = value;
        this.color = color;
        this.size = size;
    }
}

class MyView extends View {
    Paint pnt;
    ArrayList<Point> arrP;
    final int START = 0;
    final int MOVE = 1;
    int value, color, reStart, reEnd, size;

    Bitmap bit;

    public MyView(Context context) {
        super(context);
        pnt = new Paint(Paint.ANTI_ALIAS_FLAG);
        pnt.setStrokeJoin(Paint.Join.ROUND);
        pnt.setStrokeCap(Paint.Cap.ROUND);
        arrP = new ArrayList<>();
        color = Color.BLACK;
        bit = null;
        size = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if(bit!=null) {
            canvas.drawBitmap(bit, 0, 0, null);
        }

        for(int i = 0; i < arrP.size(); i++) {
            pnt.setColor(arrP.get(i).color);
            pnt.setStrokeWidth(arrP.get(i).size);
            if (arrP.get(i).pValue == MOVE) {
                canvas.drawLine(arrP.get(i-1).x, arrP.get(i-1).y, arrP.get(i).x, arrP.get(i).y, pnt);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            value = START;
            arrP.add(new Point(event.getX(), event.getY(), value, color, size));
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            value = MOVE;
            arrP.add(new Point(event.getX(), event.getY(), value, color, size));
            invalidate();
            return true;
        }
        return false;
    }
}

