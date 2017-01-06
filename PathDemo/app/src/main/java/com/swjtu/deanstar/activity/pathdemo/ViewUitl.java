package com.swjtu.deanstar.activity.pathdemo;


import android.content.Context;
import android.util.Log;
import android.view.View.MeasureSpec;

/**
 * Created by yhp5210 on 2016/12/14.
 */

public class ViewUitl {

    private static final String TAG = "ViewUitl";

    public static int getSize(int SizeInfoMeasureSpec,int limitSize){

        int mode = MeasureSpec.getMode(SizeInfoMeasureSpec);
        int size = limitSize;
        switch(mode){
            case MeasureSpec.EXACTLY:{
                size = MeasureSpec.getSize(SizeInfoMeasureSpec);
                Log.d(TAG,"exactly");

            }break;
            case MeasureSpec.AT_MOST:{
                size = Math.min(size,MeasureSpec.getSize(SizeInfoMeasureSpec));
                Log.d(TAG,"at_most");
            }break;
            default:{
                size = limitSize;
                Log.d(TAG,"else");
            }break;
        }
        return size;
    }

    public static int dip2px(Context context, float dpValue) {

         float scale = context.getResources().getDisplayMetrics().density;

        return (int)(dpValue*scale+0.5f);

    }
    public static int px2dip(Context context,float pxValue) {

        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);

    }


}
