package com.sample.tv.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by hzsunyj on 16/7/8.
 */
public class BrowseFrameLayout extends android.support.v17.leanback.widget.BrowseFrameLayout {
    public BrowseFrameLayout(Context context) {
        super(context);
    }

    public BrowseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BrowseFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Sets a {@link OnFocusSearchListener}.
     */
    public void setOnFocusSearchListener(OnFocusSearchListener listener) {

    }

//    @Override
    //    public View focusSearch(View focused, int direction) {
    //        if (direction == FOCUS_UP) {
    //        }
    //        return super.focusSearch(focused, direction);
    //    }
}
