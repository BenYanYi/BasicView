package com.mylove.basicview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * @author yanyi
 * @date 2018/8/17 14:38
 * @email ben@yanyi.red
 * @overview 圆环进度条
 */
public class CircleProgress extends View {
    private Context mContext;//上下文
    private Paint mPaint;//画笔
    private Paint bPaint;//背景画笔
    private int[] colors = new int[]{Color.parseColor("#00ff00"), Color.parseColor("#ffff00"), Color.parseColor("#ff0000")};
    private float maxSize = 100;//最大值
    private float minSize = 0;//最小值
    private float size = minSize;//当前值
    private float number = minSize;//当前值
    private float startAngle = 150;//开始位置
    private float angle = 240;//圆环的范围
    private float proportion;//一份所占的角度
    private TextView tv;//中间显示的文字
    private float msgSize = minSize;//显示时的值
    private int dip;
    private boolean isShader = true;//是否让进度条渐变，默认渐变
    private int defaultColor = Color.RED;//圆环画笔默认颜色
    private float defaultWidth = 16;//圆环画笔线宽
    private int bgColor = Color.GRAY;//背景圆环颜色
    private float bgWidth = 16;//背景圆环线宽
    private String leftMsg = "";//左边文字
    private String rightMsg = "";//右边文字
    private boolean isDecimal = true;//显示小数位
    private boolean isEquilateral = false;//是否等边

    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgress);
        maxSize = ta.getFloat(R.styleable.CircleProgress_cpMaxSize, 100);
        minSize = ta.getFloat(R.styleable.CircleProgress_cpMinSize, 0);
        startAngle = ta.getFloat(R.styleable.CircleProgress_cpStartAngle, 150);
        angle = ta.getFloat(R.styleable.CircleProgress_cpAngle, 240);
        isShader = ta.getBoolean(R.styleable.CircleProgress_cpIsShader, true);
        defaultColor = ta.getColor(R.styleable.CircleProgress_cpDefaultColor, Color.RED);
        defaultWidth = ta.getFloat(R.styleable.CircleProgress_cpDefaultWidth, 16);
        bgColor = ta.getColor(R.styleable.CircleProgress_cpBgColor, Color.GRAY);
        bgWidth = ta.getFloat(R.styleable.CircleProgress_cpBgWidth, 16);
        leftMsg = ta.getString(R.styleable.CircleProgress_cpLeftMsg);
        rightMsg = ta.getString(R.styleable.CircleProgress_cpRightMsg);
        isDecimal = ta.getBoolean(R.styleable.CircleProgress_cpIsDecimal, true);
        isEquilateral = ta.getBoolean(R.styleable.CircleProgress_cpEquilateral, false);
        size = minSize;
        number = minSize;
        msgSize = minSize;
        setPaint();
    }

    /**
     * 设置画笔
     */
    private void setPaint() {
        //设置进度条画笔
        mPaint = new Paint();
        //设置画笔颜色
        mPaint.setColor(defaultColor);
        //设置画笔无锯齿
        mPaint.setAntiAlias(true);
        //线宽
        mPaint.setStrokeWidth(defaultWidth);
        //只绘制轮廓
        mPaint.setStyle(Paint.Style.STROKE);
        //设置圆角
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置背景画笔
        bPaint = new Paint();
        //设置画笔颜色
        bPaint.setColor(bgColor);
        //设置画笔无锯齿
        bPaint.setAntiAlias(true);
        //线宽
        bPaint.setStrokeWidth(bgWidth);
        //只绘制轮廓
        bPaint.setStyle(Paint.Style.STROKE);
        //设置圆角
        bPaint.setStrokeCap(Paint.Cap.ROUND);

        dip = DisplayUtils.dip2px(mContext, 20);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算值为1时所占角度的大小
        proportion = angle / (maxSize - minSize);
        //计算当前值对应的角度
        float mSweepAngle = (number - minSize) * proportion;
        //绘制渐变
//        Shader sweepGradient = new SweepGradient(getWidth() / 2, getHeight() / 2, colors, null);
//        mPaint.setShader(sweepGradient);
        if (isShader) {
            LinearGradient mGradient = new LinearGradient(dip, dip, getWidth() - dip, dip, colors, null, LinearGradient.TileMode.CLAMP);
            mPaint.setShader(mGradient);
        }
        //圆弧所在范围的外矩形
        RectF rectF = new RectF(dip, dip, getWidth() - dip, getHeight() - dip);
        //背景圆弧所在的椭圆对象，开始角度，圆弧角度，是否与圆心连线，画笔
        canvas.drawArc(rectF, startAngle, angle, false, bPaint);
        //圆弧所在的椭圆对象，开始角度，圆弧角度，是否显示连线（圆弧两边与圆心的连线）,画笔
        canvas.drawArc(rectF, startAngle, mSweepAngle, false, mPaint);
        canvas.rotate(150, getWidth() / 2, getHeight() / 2);

        //设置显示的时候保留两位小数
        DecimalFormat mFormat = new DecimalFormat("##0");
        if (isDecimal) {
            mFormat = new DecimalFormat("##0.00");
        }
        //设置文字
        String msg = leftMsg + mFormat.format(msgSize) + rightMsg;
        if (tv != null) {
            tv.setText(msg);
        }
        setData();
    }

    /**
     * 数据处理
     */
    private void setData() {
        //判断当前数值是否超过设置的最大值，简单点就是判断当前值最后的角度是否会超过最大角度
        if (size >= maxSize) {
            if (msgSize >= maxSize && msgSize < size) {
                number = maxSize;
                msgSize = msgSize + proportion;
                invalidate();
            } else if (msgSize == size) {
                number = maxSize;
                msgSize = size;
                invalidate();
            } else if (msgSize > (size + proportion)) {
                msgSize = msgSize - proportion;
                number = maxSize;
                invalidate();
            } else if (msgSize > size && msgSize < (size + proportion)) {//当进度计算过头归为填入数值
                msgSize = size;
                number = maxSize;
                invalidate();
            } else {
                msgSize = msgSize + proportion;
                number = msgSize;
                invalidate();
            }
        } else if (size >= minSize) {
            //判断当前增加的角度是否超过数值对应的角度
            if (msgSize < size) {
                msgSize = msgSize + proportion;
                number = msgSize;
                invalidate();
            } else if (msgSize == size) {
                msgSize = size;
                number = msgSize;
                invalidate();
            } else if (msgSize > size && msgSize < (size + proportion)) {
                msgSize = size;
                number = msgSize;
                invalidate();
            } else {
                msgSize = msgSize - proportion;
                number = msgSize;
                invalidate();
            }
        } else {
            msgSize = size;
            number = minSize;
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽度的模式与大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //获取高度的模式与大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = measureWidth(widthMode, width);
        int heightSize = measureHeight(heightMode, height);
//        if (isEquilateral) {
//            if (widthSize >= heightSize) {
//                setMeasuredDimension(heightSize, heightSize);
//            } else {
//                setMeasuredDimension(widthSize, widthSize);
//            }
//        } else {
        setMeasuredDimension(widthSize, heightSize);
//        }
    }

    /**
     * 判断宽度
     *
     * @param mode  宽度模式
     * @param width 宽度
     */
    private int measureWidth(int mode, int width) {
        int mWidth = DisplayUtils.getWindow_Width(mContext) / 5 * 3;
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mWidth = width;
                break;
        }
        return mWidth;
    }

    /**
     * 判断高度
     *
     * @param mode   高度模式
     * @param height 高度
     */
    private int measureHeight(int mode, int height) {
        int mHeight = DisplayUtils.getWindow_Width(mContext) / 5 * 3;
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mHeight = height;
                break;
        }
        return mHeight;
    }

    /**
     * 设置最大值
     *
     * @param maxSize 最大值
     */
    public CircleProgress setMaxSize(float maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    /**
     * 设置最小值
     *
     * @param minSize 最小值
     */
    public CircleProgress setMinSize(float minSize) {
        this.minSize = minSize;
        this.number = minSize;
        return this;
    }

    /**
     * 设置渐变颜色
     *
     * @param colors 颜色数组
     */
    public CircleProgress setColors(int[] colors) {
        this.colors = colors;
        return this;
    }

    /**
     * 起始点位置
     *
     * @param startAngle 多少度处（默认150°）
     */
    public CircleProgress setStartAngle(float startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    /**
     * 设置圆环显示大小，最大360°
     *
     * @param angle 度数（默认240）
     */
    public CircleProgress setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    /**
     * 设置是否需要颜色渐变
     *
     * @param isShader 默认为true，需要
     */
    public CircleProgress isShader(boolean isShader) {
        this.isShader = isShader;
        return this;
    }

    /**
     * 设置圆环画笔颜色
     *
     * @param defaultColor 颜色,默认为红色
     */
    public CircleProgress setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        return this;
    }

    /**
     * 设置圆环画笔线宽
     *
     * @param defaultWidth 圆环画笔线宽，默认为16px
     */
    public CircleProgress setDefaultWidth(float defaultWidth) {
        this.defaultWidth = defaultWidth;
        return this;
    }

    /**
     * 设置背景圆环画笔颜色
     *
     * @param bgColor 背景圆环颜色，默认灰色
     */
    public CircleProgress setBgColor(int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    /**
     * 设置背景圆环线宽
     *
     * @param bgWidth 背景圆环线宽，默认16px
     */
    public CircleProgress setBgWidth(float bgWidth) {
        this.bgWidth = bgWidth;
        return this;
    }

    /**
     * 设置左边文本
     *
     * @param leftMsg 文本
     */
    public CircleProgress setLeftMsg(String leftMsg) {
        this.leftMsg = leftMsg;
        return this;
    }

    /**
     * 设置右边文本
     *
     * @param rightMsg 文本
     */
    public CircleProgress setRightMsg(String rightMsg) {
        this.rightMsg = rightMsg;
        return this;
    }

    /**
     * 设置是否显示小数
     *
     * @param isDecimal 默认显示
     */
    public CircleProgress isDecimal(boolean isDecimal) {
        this.isDecimal = isDecimal;
        return this;
    }

    /**
     * 设置数值
     *
     * @param number 数值
     */
    public void setNumber(float number, TextView textView) {
        this.tv = textView;
        if (number == size)
            return;
        size = number;
        invalidate();
    }

    /**
     * 返回是否等边
     */
    public boolean isEquilateral() {
        return isEquilateral;
    }

    /**
     * 设置是否等边
     */
    public void setEquilateral(boolean equilateral) {
        isEquilateral = equilateral;
    }
}
