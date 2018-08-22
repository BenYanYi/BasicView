# BasicView
自定义控件

### 使用方法
        compile 'com.yanyi.benyanyi:basicview:1.0.2'
        
### 简介

#### CircleProgress
环形进度条，中间的显示文本需要自己添加，可以为空，为空时不显示<br/>
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
* 