package test.bwie.com.yuekao.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import test.bwie.com.yuekao.SecondActivity;

/**
 * Created by dell on 2017/10/25.
 */

public class MyBo extends View {public MyBo(Context context) {
    super(context);
}

    public MyBo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        int verticalCenter    =  getHeight() / 2;
        int horizontalCenter  =  getWidth() / 2;
        int circleRadius      = 50;
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.BLUE);
        canvas.drawCircle( horizontalCenter, verticalCenter, circleRadius, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("ss","点击了小圆 开始属性动画");
                // X轴方向上的坐标
                float translationX = this.getTranslationX();
                //Y轴上坐标
                float translationY = this.getTranslationY();

                // 向右移动500pix，然后再移动到原来的位置复原。
                // 参数“translationX”指明在x坐标轴位移，即水平位移。
                ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationX", translationX, 500f);
                //同上
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(this, "translationY", translationY, 800f);

                anim.setDuration(5000);
                anim2.setDuration(5000);
                anim2.start();
                // 正式开始启动执行动画
                anim.start();

                //添加动画的生命周期
                anim2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Log.i("ss","属性动画结束");
                        //结束时跳转界面
                        Intent it = new Intent(getContext(), SecondActivity.class);
                        getContext().startActivity(it);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                break;
        }
        return true;
    }
}
