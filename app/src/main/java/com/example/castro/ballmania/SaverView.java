package com.example.castro.ballmania;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Renne on 11/22/2016.
 */

public class SaverView extends View {
    private Bitmap bmp;
    private Canvas myCanvas;
    private Paint paint;
    private ArrayList<Ball> balls;
    private double nx, ny, px, py; //Next and previous position onTouch

    public SaverView(Context context) {
        super(context);

        bmp = Bitmap.createBitmap(
                2560,
                1600,
                Bitmap.Config.RGB_565
        );
        myCanvas = new Canvas(bmp);

        balls = new ArrayList<Ball>();

        paint = new Paint();
        paint.setTextSize(80);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(balls.size()> 125) {
            paint.setTextSize(140);
            System.out.println(SaverView.width );
            shadowText("Max Relaxation Reached!", SaverView.width / 4 - 100, SaverView.height / 2 - 100,Color.GREEN);
            paint.setColor(Color.BLACK);
        }else {
            myCanvas.drawColor(Color.WHITE);
            for (Ball b : balls) {
                b.move();
            }

            drawDashBoard();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Log.d("CRAP", "Hell has frozen over: " + e.toString());
            }
            invalidate();  // Force a re-draw
        }
        canvas.drawBitmap(bmp, 0, 0, paint);


    }
    int a = 0;
    private void drawDashBoard(){
        shadowText("Balls: " + balls.size(),50,100, Color.YELLOW);

        //Progress Bar
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        myCanvas.drawRect(424,14,424 + 1000,114,paint);
        paint.setColor(Color.YELLOW);
        myCanvas.drawRect(420,10,420 + 1000,110,paint);
        paint.setStyle(Paint.Style.FILL);
        int c = (balls.size() < 126) ? balls.size() * 2: 255;
        paint.setColor(Color.rgb(255 - c, c, 0));
        int size = (balls.size() < 126) ? balls.size() * 8 : 998;
        myCanvas.drawRect(422,12,422 + size,108,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                //When the user presses on the screen
                //we will do something here
                nx = event.getRawX(); // The current calculation
                ny = event.getRawY();

                double cx = (nx - px);
                double cy = (ny - py);

                double angle = Math.atan(cx/cy) - Math.PI / 2;

                if( cy < 0) {
                    angle += Math.PI;
                }

                if(balls.size()<126){
                    balls.add(new Ball((int)nx,(int)ny,angle, myCanvas, paint));
                }


                break;
            case MotionEvent.ACTION_DOWN:
                //When the user releases the screen
                //do something here
                px = event.getRawX(); // The current calculation
                py = event.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }
        return true;
    }
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        SaverView.width = w;
        SaverView.height = h;
        bmp = Bitmap.createBitmap(
                SaverView.width,
                SaverView.height,
                Bitmap.Config.RGB_565
        );
        myCanvas = new Canvas(bmp);
    }

    public void shadowText(String text, int x, int y, int c){
        paint.setColor(Color.BLACK);
        myCanvas.drawText(text ,x,y,paint);
        paint.setColor(c);
        myCanvas.drawText(text ,x - 2,y - 2,paint);
    }
    public static int width;
    public static int height;

}
