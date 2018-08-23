# BasicView
自定义控件

### 使用方法
      compile 'com.yanyi.benyanyi:basicview:1.0.2'
        
### 简介

#### CircleProgress
环形进度条，显示文本需要自己添加，可以为空，为空时不显示当前进度值<br/>
![](https://github.com/BenYanYi/BasicView/blob/master/gif/circleView.gif)
##### 简单使用
xml中添加

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.mylove.basicview.CircleProgress
            android:id="@+id/viewCircleProgress"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

        <TextView
            android:id="@+id/viewTextViewMsg"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true" />
    </RelativeLayout>
    
逻辑代码中
    
      CircleProgress  circle = findViewById(R.id.viewCircleProgress);
      TextView tv = findViewById(R.id.viewTextViewMsg);
      circle.setNumber(your size,tv);
      
##### 配置
###### xml配置
* cpMaxSize 设置最大值(默认为100)
* cpMinSize 设置最小值(默认为0)
* cpStartAngle 起始位置（角度）（默认150°的位置）
* cpAngle 圆环大小(角度)(默认270°)
* cpIsShader 是否显示渐变进度条（默认显示）
* cpDefaultColor 圆环画笔颜色（默认为红色）
* cpDefaultWidth 圆环画笔线宽（默认为16）
* cpBgColor 背景画笔颜色（默认为灰色）
* cpBgWidth 背景画笔线宽（默认为16）
* cpLeftMsg 左边文字（默认为“”）
* cpRightMsg 右边文字（默认为“”）
* cpIsDecimal 是否显示小数（默认显示）

###### 逻辑代码配置
* setMaxSize(float maxSize) 设置最大值（默认为100）
* setMinSize(float minSize) 设置最小值（默认为0）
* setColors(int[] colors) 设置渐变颜色（颜色数组）
* setStartAngle(float startAngle) 起始点位置（默认为圆的150°处）
* setAngle(float angle) 设置圆环显示大小（默认显示240°角度大小）
* isShader(boolean isShader) 设置是否需要显示渐变颜色（默认显示）
* setDefaultColor(int defaultColor) 设置圆环画笔颜色（默认为红色）
* setDefaultWidth(float defaultWidth) 设置圆环画笔线宽（默认为16）
* setBgColor(int bgColor) 设置背景圆环画笔颜色（默认为灰色）
* setBgWidth(float bgWidth) 设置背景圆环画笔线宽（默认为16）
* setLeftMsg(String leftMsg) 设置左边文本（默认为“”）
* setRightMsg(String rightMsg) 设置右边文本（默认为“”）
* isDecimal(boolean isDecimal) 设置是否显示小数（默认显示）
* setNumber(float number, TextView textView) 设置数值（第一个参数为数值，第二的参数为进度显示的文本，可以为空。这个设置只能放在最后，要不然前面的配置都不起作用）

#### CircleView
环形进度条，中间显示文本
##### 简单使用
xml中添加

	<com.mylove.basicview.CircleView
        android:id="@+id/circle"
        android:layout_width="100dp"
        android:layout_height="100dp" />