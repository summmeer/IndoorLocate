package com.sansa.locat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by think on 2018/4/27.
 */

public class MyMatrixImg extends AppCompatImageView {
    private Context mContext;
//    private ImageView animationIV;
//    private AnimationDrawable animationDrawable;
    private Matrix currentMatrix, savedMatrix;// Matrix对象

    private PointF startF= new PointF();
    private PointF midF;// 起点、中点对象

    // 初始的两个手指按下的触摸点的距离
    private float oldDis = 1f;

    private static final int MODE_NONE = 0;// 默认的触摸模式
    private static final int MODE_DRAG = 1;// 拖拽模式
    private static final int MODE_ZOOM = 2;// 缩放模式
    private int mode = MODE_NONE;


    public MyMatrixImg(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        // 初始化
        init();
    }

    private void init() {
        /*
         * 实例化对象
         */
        currentMatrix = new Matrix();
        savedMatrix = new Matrix();

        /*
         * 获取屏幕宽高
         */

        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int Screenwidth = outMetrics.widthPixels;
        int Screenheight = outMetrics.heightPixels;

        /*
         * 设置图片资源
         */
        //start the animation
        //animationIV = (ImageView) findViewById(R.id.animationIV);
        //animationIV.setImageResource(R.drawable.prod_point_img);
        //animationDrawable = (AnimationDrawable) animationIV.getDrawable();
        //animationDrawable.start();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_s).copy(Bitmap.Config.ARGB_8888, true);;
//        Bitmap pointPic = BitmapFactory.decodeResource(getResources(), R.drawable.point_img).copy(Bitmap.Config.ARGB_8888, true);
//        Canvas canvas=new Canvas(bitmap);
//
//        int left = 1830;
//        int top = 4350;
//        canvas.drawBitmap(pointPic, left, top, null);

        bitmap = Bitmap.createScaledBitmap(bitmap, Screenwidth, Math.round(Screenwidth * 1620 / 1080), true);
        setImageBitmap(bitmap);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()& MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:// 单点接触屏幕时
//                savedMatrix.set(currentMatrix);
//                startF.set(event.getX(), event.getY());
//                mode=MODE_DRAG;
//                break;
//
//            case MotionEvent.ACTION_POINTER_DOWN:// 第二个手指按下事件
//                oldDis = calDis(event);
//                if (oldDis > 10F) {
//                    savedMatrix.set(currentMatrix);
//                    midF=calMidPoint(event);
//                    mode = MODE_ZOOM;
//                }
//
//                break;
//            case MotionEvent.ACTION_MOVE:// 触摸点移动时
//                /*
//                 * 单点触控拖拽平移
//                 */
//
//                if (mode == MODE_DRAG) {
//                    currentMatrix.set(savedMatrix);
//                    float dx = event.getX() - startF.x;
//                    float dy = event.getY() - startF.y;
//                    currentMatrix.postTranslate(dx, dy);
//                }
//                /*
//                 * 两点触控拖放
//                 */
//                else if(mode == MODE_ZOOM && event.getPointerCount() == 2){
//                    float newDis = calDis(event);
//                    currentMatrix.set(savedMatrix);
//
//                    //指尖移动距离大于10F缩放
//                    if (newDis > 10F) {
//                        //通过先后两次距离比计算出缩放的比例
//                        float scale = newDis / oldDis;
//                        currentMatrix.postScale(scale, scale, midF.x, midF.y);
//                    }
//                }
//
//                break;
//            case MotionEvent.ACTION_UP:// 单点离开屏幕时
//                mode=MODE_NONE;
//                break;
//            case MotionEvent.ACTION_POINTER_UP:// 第二个点离开屏幕时
//                savedMatrix.set(currentMatrix);
//                if(event.getActionIndex()==0)
//                    startF.set(event.getX(1), event.getY(1));
//                else if(event.getActionIndex()==1)
//                    startF.set(event.getX(0), event.getY(0));
//                mode=MODE_DRAG;
//                break;
//        }
//
//        setImageMatrix(currentMatrix);
//        return true;
//    }
//
//    // 计算两个触摸点之间的距离
//    private float calDis(MotionEvent event) {
//        float x = event.getX(0) - event.getX(1);
//        float y = event.getY(0) - event.getY(1);
//        return (float) Math.sqrt(x * x + y * y);
//    }
//
//    // 计算两个触摸点的中点
//    private PointF calMidPoint(MotionEvent event) {
//        float x = event.getX(0) + event.getX(1);
//        float y = event.getY(0) + event.getY(1);
//        return new PointF(x / 2, y / 2);
//    }
}
