package com.swjtu.deanstar.activity.pathdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yhp5210 on 2016/12/26.
 */

public class LolAbilityView extends View{

    private static final String TAG = "LolAbilityView";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int STEP = 100;
    private static final String COLOR1 = "#278892";
    private static final String COLOR2 = "#57c1c8";
    private static final String COLOR3 = "#99dde3";
    private static final String COLOR4 = "#d4f0f2";
    private static final String LINECOLOR = "#a5dbdf";
    private static final String DATALINECOLOR = "#ec6051";
    private static final String[] colors = {COLOR1,COLOR2,COLOR3,COLOR4};
    private static final float STROKE_WIDTH = 9.0f;
    private static final float DATALINE_STROKE_WIDTH = 15.0f;
    private int mWidth;
    private int mHeight;
    private Paint mPiant,mDataPaint;
    private float mTextSize = 50;
    private static Item[] items;
    private Path mDataPath;

    public LolAbilityView(Context context) {
        super(context);
        initResources();
    }
    public LolAbilityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources();
    }
    public LolAbilityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mWidth = ViewUitl.getSize(widthMeasureSpec,WIDTH);
        mHeight = ViewUitl.getSize(heightMeasureSpec,HEIGHT);
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        mPiant.setColor(Color.parseColor(COLOR1));
       // drawXy(canvas);
        drawOutLine(canvas);
        drawLines(canvas);
        drawData(canvas);
        canvas.save();
    }

    /**
     * 画放射线
     * @param canvas
     */
    public void drawLines(Canvas canvas){
        mPiant.setColor(Color.parseColor(LINECOLOR));
        for(int i = 0;i < items.length;i ++){
            canvas.drawLine(0,0,0,-4*STEP,mPiant);
            canvas.rotate(360/7.0f);

        }
    }

    public void drawData(Canvas canvas){

        float lineLen,x,y;
        for(int i = 0;i < items.length+1;i ++){

            lineLen = 4*STEP*(items[i%items.length].getCurValue()/(1.0f*items[i%items.length].getTotalValue()));
            x =(float)Math.cos((270+i*(360/7.0f))/180.0f*Math.PI) * lineLen;
            y = (float)Math.sin((270+i*(360/7.0f))/180.0f*Math.PI) * lineLen;
            if(0 == i){
                mDataPath.moveTo(x,y);
            }else{
                mDataPath.lineTo(x,y);
            }
        }
        canvas.drawPath(mDataPath,mDataPaint);

    }

    public void drawOutLine(Canvas canvas){
        for(int j = 4;j > 0;j --){
            Path path = new Path();
            path.moveTo(0,-j*STEP);
            mPiant.setStyle(Paint.Style.FILL);
            for(int i = 0;i < items.length;i ++){

                float x = (float)Math.cos((270+i*(360/7.0f))/180.0f*Math.PI)*j*STEP;
                float y = (float)Math.sin((270+i*(360/7.0f))/180.0f*Math.PI)*j*STEP;
                path.lineTo(x,y);
                mPiant.setColor(Color.BLACK);
                mPiant.setTextSize(mTextSize);
                if(j != 4){
                    continue;
                }
                if(i == 0){
                    mPiant.setTextAlign(Paint.Align.CENTER);
                }else if(i <= 7/2){
                    mPiant.setTextAlign(Paint.Align.LEFT);
                }else{
                    mPiant.setTextAlign(Paint.Align.RIGHT);
                }
                float charX,charY;
                if(y > 0){
                    charX = (float)Math.cos((270+i*(360/7.0f))/180.0f*Math.PI)*(j+0.3f)*(STEP);
                    charY = (float)Math.sin((270+i*(360/7.0f))/180.0f*Math.PI)*(j+0.3f)*(STEP+mTextSize/5);
                }else{
                    charX = (float)Math.cos((270+i*(360/7.0f))/180.0f*Math.PI)*(j+0.3f)*STEP;
                    charY = (float)Math.sin((270+i*(360/7.0f))/180.0f*Math.PI)*(j+0.3f)*STEP;
                }

                canvas.drawText(items[i].getTitle(),charX,charY,mPiant);

            }
            mPiant.setColor(Color.parseColor(colors[j-1]));
            canvas.drawPath(path,mPiant);
        }

    }

    /**
     * 画辅助坐标系
     * @param canvas
     */
    public void drawXy(Canvas canvas){


        int dimision = 20;
        int arrowDimision = 20;
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        textPaint.setStrokeWidth(2.0f);
        canvas.drawLine(-mWidth/2+dimision,0,mWidth/2-dimision,0,textPaint);//画X轴
        canvas.drawLine(0,-mHeight/2+dimision,0,mHeight/2-dimision,textPaint);//画Y轴
        textPaint.setStrokeWidth(3.0f);
        float[] yArrow = {-arrowDimision,-mHeight/2+arrowDimision+dimision,0,-mHeight/2+dimision,0,-mHeight/2+dimision,arrowDimision,-mHeight/2+arrowDimision+dimision};
        canvas.drawLines(yArrow,textPaint);
        float[] xArrow = {mWidth/2-dimision-arrowDimision,-arrowDimision,mWidth/2-dimision,0,mWidth/2-dimision,0,mWidth/2-dimision-arrowDimision,arrowDimision};
        canvas.drawLines(xArrow,textPaint);
        canvas.drawText("0",-50,50,textPaint);
    }

    public void initResources(){
        mPiant = new Paint();
        mPiant.setStrokeWidth(STROKE_WIDTH);
        mPiant.setStyle(Paint.Style.STROKE);
        mDataPath = new Path();
        items = new Item[0];
        mDataPaint = new Paint();
        mDataPaint.setColor(Color.parseColor(DATALINECOLOR));
        mDataPaint.setStrokeWidth(DATALINE_STROKE_WIDTH);
        mDataPaint.setStyle(Paint.Style.STROKE);

    }

    /**
     * 绑定数据，必须调用
     */
    public void bindData(Item[] items){
        this.items = items;
        invalidate();
    }



}
class Item{

    private String title;
    private int curValue;
    private int totalValue;

    public Item(String title, int curValue, int totalValue) {
        this.title = title;
        this.curValue = curValue;
        this.totalValue = totalValue;
    }

    public String getTitle() {
        return title;
    }

    public int getCurValue() {
        return curValue;
    }

    public int getTotalValue() {
        return totalValue;
    }
}