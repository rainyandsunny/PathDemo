package com.swjtu.deanstar.activity.pathdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yhp5210 on 2016/12/26.
 */

public class PathViewDemo extends View {

    private static final String TAG = "PathViewDemo";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private int width;
    private int height;
    private Paint mPaint;

    public PathViewDemo(Context context) {
        super(context);
        initResources();
    }
    public PathViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources();
    }
    public PathViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = ViewUitl.getSize(widthMeasureSpec,WIDTH);
        height = ViewUitl.getSize(heightMeasureSpec,HEIGHT);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        test6(canvas);

    }

    public void initResources(){

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);
    }


    /**
     * moveTo和LineTo的区别
     * //方法名	           简介	                       是否影响之前的操作	是否影响之后操作
     //moveTo	        移动下一次操作的起点位置	             否	              是
     // setLastPoint	设置之前操作的最后一个点位置	         是	              是
     *
     */
    public void test1(Canvas canvas){
        Path path = new Path();
        canvas.translate(width/2,height/2);
        //使用moveTo
        path.lineTo(200,200);
        path.moveTo(200,100);
        path.lineTo(200,0);
        canvas.drawPath(path,mPaint);
        //------------------------------------------------------------------
        //使用setLastPoint
        path.lineTo(200, 200);                      // lineTo
        path.setLastPoint(200,100);                 // setLastPoint
        path.lineTo(200,0);
        canvas.drawPath(path,mPaint);

    }

    /**
     * close将path闭合
     * @param canvas
     */
    public void test2(Canvas canvas){
        Path path = new Path();
        canvas.translate(width/2,height/2);
        path.lineTo(200,200);
        path.lineTo(200,0);
        path.close();
        canvas.drawPath(path,mPaint);

    }

    /**
     * Direction的区别
     * CCW的效果见ccw.png
     * CW的效果见cw.png
     */
    public void test3(Canvas canvas){

        Path path = new Path();
        canvas.translate(width / 2, height / 2);  // 移动坐标系到屏幕中心
        path.addRect(-200,-200,200,200, Path.Direction.CW);
        path.setLastPoint(-300,300);
        canvas.drawPath(path,mPaint);
    }


    /**
     * addArc和arcTo的区别
     * 名称	作用	区别
     addArc	添加一个圆弧到path	直接添加一个圆弧到path中
     arcTo	添加一个圆弧到path	添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
     addArc的效果见addarc.png
     arcTo的效果见arcto.png
     * @param canvas
     */
    public void test4(Canvas canvas){

        canvas.translate(width/2,height/2);
        canvas.scale(1,-1);
        Path path = new Path();
        path.lineTo(100,100);
        RectF oval = new RectF(0,0,300,300);
       // path.addArc(oval,0,270)
        path.arcTo(oval,0,270);//相当于path.arcTo(oval,0,270,false)
        canvas.drawPath(path,mPaint);
    }

    /**
     * isEmpty方法
     * @param canvas
     */
    public void test5(Canvas canvas){
        Path path = new Path();
        Log.e(TAG,"1path.isEmpty():"+path.isEmpty());
        path.lineTo(100,100);
        Log.e(TAG,"2path.isEmpty():"+path.isEmpty());
        canvas.drawPath(path,mPaint);
    }

    /**
     * 判断一个path是不是矩形，是的话则存进rect中
     * @param canvas
     */
    public void test6(Canvas canvas){
        Path path = new Path();
        path.lineTo(0,400);
        path.lineTo(400,400);
        path.lineTo(400,0);
        path.lineTo(0,0);

        RectF rect = new RectF();
        boolean b = path.isRect(rect);
        Log.e(TAG,"isRect:"+b+"| left:"+rect.left+"| top:"+rect.top+"| right:"+rect.right+"| bottom:"+rect.bottom);
    }
}
