package com.example.castro.ballmania;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Renne on 11/22/2016.
 */

public class Ball {
    private int x,y,s,g,dx,dy,ds,dg;
    private Canvas myCanvas;
    private Paint paint;

    public Ball(int x, int y, double angle, Canvas myCanvas, Paint paint) {
        this.x = x;
        this.y = y;
        this.s = 20;
        this.g = 10;
        this.dx = (int)(20 * Math.cos(angle));
        this.dy = -(int)(20 * Math.sin(angle));
        this.ds = 1;
        this.dg = 1;
        this.myCanvas = myCanvas;
        this.paint = paint;

    }

    public void update(){
        g += dg;
        x += dx;
        y += dy;
        s += ds;
        if(g > 254 || g < 1) dg = -dg;
        if(x > SaverView.width || x < 0) dx = -dx;
        if(y > SaverView.height || y < 0) dy = -dy;
        if(s > 100 || s < 20) ds = -ds;
    }
    public void move(){
        paint.setColor(Color.rgb(0, g, 0));
        myCanvas.drawCircle(x, y, s, paint);
        update();
    }
    public int getRadius(){
        return s;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
