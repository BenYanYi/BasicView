package com.mylove.basicview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * @author yanyi
 * @date 2018/8/22 14:33
 * @email ben@yanyi.red
 * @overview 圆环进度条
 */
public class CircleView extends RadioGroup {
    private Context mContext;

    private CircleProgress circleProgress;
    private TextView textView;

    private boolean isEquilateral = false;//是否等边

    private float size;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View mView = LayoutInflater.from(context).inflate(R.layout.view_circleview, null);
        circleProgress = mView.findViewById(R.id.viewCircleProgress);
        textView = mView.findViewById(R.id.viewTextViewMsg);
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.CircleView);
        setAttrs(ta);
        addView(mView);
        circleProgress.setNumber(size, textView);
    }

    /**
     * xml设置配置信息
     */
    private void setAttrs(TypedArray ta) {
        //xml配置circleProgress
        float maxSize = ta.getFloat(R.styleable.CircleView_cvMaxSize, 100);
        float minSize = ta.getFloat(R.styleable.CircleView_cvMinSize, 0);
        float startAngle = ta.getFloat(R.styleable.CircleView_cvStartAngle, 150);
        float angle = ta.getFloat(R.styleable.CircleView_cvAngle, 240);
        boolean isShader = ta.getBoolean(R.styleable.CircleView_cvIsShader, true);
        int defaultColor = ta.getColor(R.styleable.CircleView_cvDefaultColor, Color.RED);
        float defaultWidth = ta.getFloat(R.styleable.CircleView_cvDefaultWidth, 16);
        int bgColor = ta.getColor(R.styleable.CircleView_cvBgColor, Color.GRAY);
        float bgWidth = ta.getFloat(R.styleable.CircleView_cvBgWidth, 16);
        String leftMsg = ta.getString(R.styleable.CircleView_cvLeftMsg);
        String rightMsg = ta.getString(R.styleable.CircleView_cvRightMsg);
        boolean isDecimal = ta.getBoolean(R.styleable.CircleView_cvIsDecimal, true);
        leftMsg = isEmpty(leftMsg) ? "" : leftMsg;
        rightMsg = isEmpty(rightMsg) ? "" : rightMsg;
        circleProgress.setMaxSize(maxSize)
                .setMinSize(minSize)
                .setStartAngle(startAngle)
                .setAngle(angle)
                .isShader(isShader)
                .setDefaultColor(defaultColor)
                .setDefaultWidth(defaultWidth)
                .setBgColor(bgColor)
                .setBgWidth(bgWidth)
                .setLeftMsg(leftMsg)
                .setRightMsg(rightMsg)
                .isDecimal(isDecimal);
        //xml配置textView
        size = ta.getFloat(R.styleable.CircleView_cvSize, minSize);
        float textSize = ta.getDimension(R.styleable.CircleView_cvTextSize, 16);
        int textColor = ta.getColor(R.styleable.CircleView_cvTextColor, Color.parseColor("#505050"));
        int textStyle = ta.getInteger(R.styleable.CircleView_cvTextStyle, 5);
        String str = leftMsg + size + rightMsg;
        textView.setText(str);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        TextPaint paint = textView.getPaint();
        if (textStyle == 1) {
            paint.setFakeBoldText(true);
        } else if (textStyle == 2) {
            paint.setTextSkewX(1);
        } else if (textStyle == 3) {
            paint.setUnderlineText(true);
        } else if (textStyle == 4) {
            paint.setStrikeThruText(true);
        } else {
            paint.setFakeBoldText(false);
            paint.setTextSkewX(0);
            paint.setUnderlineText(false);
            paint.setStrikeThruText(false);
        }
        isEquilateral = ta.getBoolean(R.styleable.CircleView_cvEquilateral, false);
        circleProgress.setEquilateral(isEquilateral);
    }

    /**
     * 判断字符串是否为空
     */
    private boolean isEmpty(String str) {
        return str == null || "null".equals(str) || str.trim().length() == 0;
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
        if (isEquilateral) {
            if (widthSize >= heightSize) {
                setMeasuredDimension(heightSize, heightSize);
            } else {
                setMeasuredDimension(widthSize, widthSize);
            }
        } else {
            setMeasuredDimension(widthSize, heightSize);
        }
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
    public CircleView setMaxSize(float maxSize) {
        circleProgress.setMaxSize(maxSize);
        return this;
    }

    /**
     * 设置最小值
     *
     * @param minSize 最小值
     */
    public CircleView setMinSize(float minSize) {
        circleProgress.setMinSize(minSize);
        return this;
    }

    /**
     * 设置渐变颜色
     *
     * @param colors 颜色数组
     */
    public CircleView setColors(int[] colors) {
        circleProgress.setColors(colors);
        return this;
    }

    /**
     * 起始点位置
     *
     * @param startAngle 多少度处（默认150°）
     */
    public CircleView setStartAngle(float startAngle) {
        circleProgress.setStartAngle(startAngle);
        return this;
    }

    /**
     * 设置圆环显示大小，最大360°
     *
     * @param angle 度数（默认240）
     */
    public CircleView setAngle(float angle) {
        circleProgress.setAngle(angle);
        return this;
    }

    /**
     * 设置是否需要颜色渐变
     *
     * @param isShader 默认为true，需要
     */
    public CircleView isShader(boolean isShader) {
        circleProgress.isShader(isShader);
        return this;
    }

    /**
     * 设置圆环画笔颜色
     *
     * @param defaultColor 颜色,默认为红色
     */
    public CircleView setDefaultColor(int defaultColor) {
        circleProgress.setDefaultColor(defaultColor);
        return this;
    }

    /**
     * 设置圆环画笔线宽
     *
     * @param defaultWidth 圆环画笔线宽，默认为16px
     */
    public CircleView setDefaultWidth(float defaultWidth) {
        circleProgress.setDefaultWidth(defaultWidth);
        return this;
    }

    /**
     * 设置背景圆环画笔颜色
     *
     * @param bgColor 背景圆环颜色，默认灰色
     */
    public CircleView setBgColor(int bgColor) {
        circleProgress.setBgColor(bgColor);
        return this;
    }

    /**
     * 设置背景圆环线宽
     *
     * @param bgWidth 背景圆环线宽，默认16px
     */
    public CircleView setBgWidth(float bgWidth) {
        circleProgress.setBgWidth(bgWidth);
        return this;
    }

    /**
     * 设置左边文本
     *
     * @param leftMsg 文本
     */
    public CircleView setLeftMsg(String leftMsg) {
        circleProgress.setLeftMsg(leftMsg);
        return this;
    }

    /**
     * 设置右边文本
     *
     * @param rightMsg 文本
     */
    public CircleView setRightMsg(String rightMsg) {
        circleProgress.setRightMsg(rightMsg);
        return this;
    }

    /**
     * 设置文本大小
     *
     * @param textSize 字体大小
     */
    public CircleView setTextSize(float textSize) {
        textView.setTextSize(textSize);
        return this;
    }

    /**
     * 设置文本颜色
     *
     * @param textColor 颜色值
     */
    public CircleView setTextColor(int textColor) {
        textView.setTextColor(textColor);
        return this;
    }

    public enum TextStyle {
        BOLD, ITALIC, UNDERLINE, STRIKE, NORMAL
    }

    /**
     * 设置字体样式
     *
     * @param textStyle 样式（BOLD：加粗，ITALIC：斜体，UNDERLINE：下划线，STRIKE：删除线，NORMAL：正常）
     */
    public CircleView setTextStyle(TextStyle textStyle) {
        TextPaint paint = textView.getPaint();
        switch (textStyle) {
            case BOLD:
                paint.setFakeBoldText(true);
                break;
            case ITALIC:
                paint.setTextSkewX(1);
                break;
            case UNDERLINE:
                paint.setUnderlineText(true);
                break;
            case STRIKE:
                paint.setStrikeThruText(true);
                break;
            case NORMAL:
            default:
                paint.setFakeBoldText(false);
                paint.setTextSkewX(0);
                paint.setUnderlineText(false);
                paint.setStrikeThruText(false);
                break;
        }
        return this;
    }

    /**
     * 设置是否显示小数
     *
     * @param isDecimal 默认显示小数
     */
    public CircleView isDecimal(boolean isDecimal) {
        circleProgress.isDecimal(isDecimal);
        return this;
    }

    /**
     * 设置数值
     *
     * @param size 数值
     */
    public void setSize(float size) {
        circleProgress.setNumber(size, textView);
    }

}
