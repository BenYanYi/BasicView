package com.mylove.basicview.vertical;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * @author yanyi
 * 横向自动滚动的文字
 */

public class ScrollTextView extends AppCompatTextView {
    public ScrollTextView(Context context) {
        super(context);
        initView();
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
        setSingleLine(true);
    }

    public boolean isFocused() {
        return true;
    }
}
