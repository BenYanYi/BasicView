# BasicView
自定义控件

### 使用方法
      compile 'com.yanyi.benyanyi:basicview:1.0.5'
        
### 简介

#### SmoothCheckBox
自定义圆形CheckBox
##### 简单使用
xml中添加

     <com.mylove.basicview.checkbox.SmoothCheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

##### 配置
###### xml配置
* BenDuration 动画持续时间
* BenStrikeWidth 边框宽度
* BenBorderColor 边框颜色
* BenTrimColor 选中状态颜色
* BenTickColor 对勾颜色
* BenTickWidth 对勾宽度

###### 逻辑代码配置
* isChecked() 判断checkbox是否选中状态
* setChecked(boolean isChecked) 设置checkbox的状态(是否选中)
* setChecked(boolean isChecked, boolean isAnimation) 设置checkbox的状态(是否选中,切换时是否有动画)
* onClick() 点击事件
* setOnCheckedChangeListener() 状态修改事件

#### TouchImageView
图片手势旋转与缩放
##### 简单使用
和ImageView使用方法一样

#### CircleProgress
环形进度条，显示文本需要自己添加，可以为空，为空时不显示当前进度值<br/>
![](https://github.com/BenYanYi/BasicView/blob/master/gif/circleProgress.gif)
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
* cpAngle 圆环大小(角度)(默认270°，最大360°，也就是一圈)
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
* setColors(int[] colors) 设置渐变颜色（颜色数组，默认绿黄红）
* setStartAngle(float startAngle) 起始点位置（默认为圆的150°处）
* setAngle(float angle) 设置圆环显示大小（默认显示240°角度大小，最大360°，也就是一圈）
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
![](https://github.com/BenYanYi/BasicView/blob/master/gif/circleView.gif)
##### 简单使用
xml中添加

	<com.mylove.basicview.CircleView
        android:id="@+id/circle"
        android:layout_width="100dp"
        android:layout_height="100dp" />

逻辑代码中
	
	CircleView circleView = findViewById(R.id.circle);
	circleView.setSize(your size);

##### 配置
###### xml配置
* cvMaxSize 设置最大值（默认为100）
* cvMinSize 设置最小值（默认为0）
* cvStartAngle 设置起始位置（默认为所在圆的150°位置）
* cvAngle 设置圆环大小（默认为角度为270°的圆环，最大360°，也就是刚好一圈）
* cvIsShader 设置是否显示渐变进度条（默认显示）
* cvDefaultColor 设置圆环画笔颜色（默认为红色）
* cvDefaultWidth 设置圆环画笔线宽（默认为16）
* cvBgColor 设置圆环背景画笔颜色（默认为灰色）
* cvBgWidth 设置圆环背景画笔线宽（默认为16）
* cvLeftMsg 设置左边文字（默认“”）
* cvRightMsg 设置右边文字（默认“”）
* cvSize 设置文本内容（默认为设置的最小值，没设置则为0）
* cvTextSize 设置字体大小（默认16）
* cvTextColor 设置字体颜色（默认为灰色）
* cvTextStyle 设置文本样式（bold：加粗；italic：斜体；underline：下划线；strike：删除线；normal：正常；默认正常）
* cvIsDecimal 设置是否显示小数（默认显示）

###### 逻辑代码配置
* setMaxSize(float maxSize) 设置最大值（默认100）
* setMinSize(float minSize) 设置最小值（默认0）
* setColors(int[] colors) 设置渐变颜色（颜色数组，默认绿黄红）
* setStartAngle(float startAngle) 设置圆环起始点位置（默认为所在圆的150°位置）
* setAngle(float angle) 设置圆环大小（默认为角度为270°的圆环）
* isShader(boolean isShader) 设置是否需要颜色渐变（默认为true，也就是需要）
* setDefaultColor(int defaultColor) 设置圆环画笔颜色（默认为红色）
* setDefaultWidth(float defaultWidth) 设置圆环画笔线宽（默认为16）
* setBgColor(int bgColor) 设置背景圆环颜色（默认为灰色）
* setBgWidth(float bgWidth) 设置背景圆环线宽（默认为16）
* setLeftMsg(String leftMsg) 设置左边文本（默认为“”）
* setRightMsg(String rightMsg) 设置右边文本（默认为“”）
* setTextSize(float textSize) 设置文字大小（默认为16）
* setTextColor(int textColor) 设置文字颜色（默认为灰色）
* setTextStyle(TextStyle textStyle) 设置文字字体样式（默认为正常。BOLD：加粗，ITALIC：斜体，UNDERLINE：下划线，STRIKE：删除线，NORMAL：正常）
* isDecimal(boolean isDecimal) 设置是否显示小数（默认显示）
* setSize(float size) 设置数值，也就是中间的文字<br/>

若在使用过程中出现什么问题，可以联系作者<br/>
作者：演绎<br/>
QQ：1541612424<br/>
email： work@yanyi.red<br/>
微信公众号：benyanyi(演绎未来)&nbsp;&nbsp;&nbsp;将会不定期的更新关于个人的一些开发经验