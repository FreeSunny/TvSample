# TV
Android TV demo

## 样例
这里我们做了一个demo，demo界面如下，以下的图都是最终运行后截出的图，由于是在模拟器上截图，
导致了控件加载不完全，但是在真是的机顶盒上是没有问题的，以下的图将就着看吧：

![这里写图片描述](http://img.blog.csdn.net/20160710223739687)
![这里写图片描述](http://img.blog.csdn.net/20160710223757411)

##question list
问题1：控件遥控器不能选中，不能导航

> 出现这种问题往往是控件没有设置android:focusable="true"属性，只有默认能够选中焦点的才不需要设置改属性，比如Button，EditText。

问题2：控件选中后，看不出选中效果

> 由于默认选中是没有视觉效果的，因此你需要对控件设置选中效果，比如说背景图片，以前在手机上可能只需要设置selector中的pressed属性，或者selected属性，现在针对TV你必须要设置focused属性，比如拨号键盘选中后会出现一个圆形的选中背景框, 如下：
![这里写图片描述](http://img.blog.csdn.net/20160710231322913)
> 
要实现上述效果，因此对每一键盘输入按钮添加如下的selector。

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:drawable="@drawable/key_board_hover" android:state_focused="true"></item>
    <item android:drawable="@drawable/key_board_hover" android:state_pressed="true"></item>
    <item android:drawable="@drawable/key_board_hover" android:state_checked="true"></item>
    <item android:drawable="@color/transparent"></item>

</selector>

key_board_hover.xml

<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="oval">
    <solid android:color="@color/white_35_transparent"></solid>
    <size
        android:width="40dp"
        android:height="40dp"/>
</shape>
```

问题3：TV launcher中没有入口图标

> 如果需要出现入口图标，你必须要在AndroidManifest中配置action为android.intent.action.MAIN，category为android.intent.category.LAUNCHER的Activity。该配置与上面的LEANBACK_LAUNCHER不冲突，可以对入口Activity配置LAUNCHER，之后一个页面配置LEANBACK_LAUNCHER，配置如下：

```
<activity
    android:name=".WelcomeActivity"
    android:label="@string/app_name"
    android:screenOrientation="landscape">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>

        <category android:name="android.intent.category.LEANBACK_LAUNCHER"/>
    </intent-filter>
</activity>
```
问题4：TV launcher中的图标不清晰，太糊

> 如果直接将手机app的launcher图标直接使用到TV中，则图标会拉伸，由于TV的图标往往都比较大，拉伸后就会变糊，因此需要重新切launcher图标，手机里面是48*48， 72*72，96*96等，而tv需要更大的尺寸，虽然没有在官方找到建议的尺寸，但是这里推荐一个尺寸180*180，可以多个文件夹都放同一个图标，这样界面加载的图标就会变得清晰。

问题5：遥控器导航下一个不是自己希望导航的控件

> 系统中如果界面中有多个可focus的控件，上下左右导航，则会找到与当前控件最邻近的控件作为下一个选中的控件，因此如果你确切想指定下一个导航的控件，则可以指定下一个控件的ID，只要该id在当前显示的界面中，比如向上 view1.setNextFocusUpId(R.id.dial_tab);

问题6：官方VerticalGridFragment加载后，默认选中第一个，但是第一个占据了整个界面。

> 该问题应该是官方的一个bug，如果不是第一次加载VerticalGridFragment，则不会出现该问题，并且我尝试了多个版本的，都会出现该问题，原因是选中后系统会在在选中的控件后插入两帧NonOverlappingView，插入的布局代码如下：

```
<merge xmlns:android="http://schemas.android.com/apk/res/android">
    <android.support.v17.leanback.widget.NonOverlappingView
        android:id="@+id/lb_shadow_normal"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/lb_card_shadow_normal" />
    <android.support.v17.leanback.widget.NonOverlappingView
        android:id="@+id/lb_shadow_focused"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/lb_card_shadow_focused"
        android:alpha="0" />

</merge>
```

该布局插入了两帧NonOverlappingView，每一帧都使用了一个.9图标作为背景，而当系统第一次加载时，最终第一个选中的控件宽高计算错误，计算成了一个16777211类似的一个值，远远超出了界面的大小，解决方案如下：

> 方案1，将布局中的match_parent改为wrap_content
> 方案2，对VerticalGridFragment中使用的VerticalGridPresenter设置ShadowEnabled，如gridPresenter.setShadowEnabled(false);
> 方案3，替换掉.9图片

问题6：官方VerticalGridFragment加载后，如果ArrayObjectAdapter使用的是自己实现的Presenter，而Presenter使用的不是系统提供的ImageCardView，则会导致选中效果不居中，当选中效果放大后会向右向下覆盖，而不是在当前位置放大覆盖四周。

> 该问题，我查了对应的style、只有针对ImageCardView的style，我也还没有仔细研究怎么调整，不过这里给出一个避免的方案，对VerticalGridPresenter选中后的高亮效果选择为不放大，如new VerticalGridPresenter(FocusHighlight.ZOOM_FACTOR_NONE)。

问题6：VerticalGridFragment顶层控件不能向上导航，比如不能导航到拨号，好友控件

> 该问题其实是被系统给拦截了。系统的VerticalGridFragment加载了lb_vertical_grid_fragment布局，该布局包含了一个BrowseFrameLayout，对
BrowseFrameLayout设置了setOnFocusSearchListener。如下：

```
    private void setupFocusSearchListener() {
        BrowseFrameLayout browseFrameLayout = (BrowseFrameLayout) getView().findViewById(
                R.id.grid_frame);
        browseFrameLayout.setOnFocusSearchListener(getTitleHelper().getOnFocusSearchListener());
    }
```

当系统在VerticalGridPresenter最顶层时，向上找最近一个控件时，发现当前布局已经没有控件，则会向父布局查找，代码如下：

```
public View focusSearch(View focused, int direction) {
    if (isRootNamespace()) {
        // root namespace means we should consider ourselves the top of the
        // tree for focus searching; otherwise we could be focus searching
        // into other tabs.  see LocalActivityManager and TabHost for more info
        return FocusFinder.getInstance().findNextFocus(this, focused, direction);
    } else if (mParent != null) {
        return mParent.focusSearch(focused, direction);
    }
    return null;
}
```

而VerticalGridPresenter的父布局则是BrowseFrameLayout，因此最终执行的是上面设置的getTitleHelper().getOnFocusSearchListener()，我们去看看改listener：

```
 private final BrowseFrameLayout.OnFocusSearchListener mOnFocusSearchListener =
            new BrowseFrameLayout.OnFocusSearchListener() {
        @Override
        public View onFocusSearch(View focused, int direction) {
            if (focused != mTitleView && direction == View.FOCUS_UP) {
                return mTitleView;
            }
            final boolean isRtl = ViewCompat.getLayoutDirection(focused) ==
                    View.LAYOUT_DIRECTION_RTL;
            final int forward = isRtl ? View.FOCUS_LEFT : View.FOCUS_RIGHT;
            if (mTitleView.hasFocus() && direction == View.FOCUS_DOWN || direction == forward) {
                return mSceneRoot;
            }
            return null;
        }
};
```

发现问题所在没有，当focused != mTitleView && direction == View.FOCUS_UP时，强制指定了mTitleView，就算没有没有显示title，效果也一样。我认为这应该算系统的一个bug，那怎么解决呐？

我们可以重写一个一模一样的lb_vertical_grid_fragment，自己写的布局会覆盖掉系统的布局，再将BrowseFrameLayout重写成我们自己的BrowseFrameLayout。如下

```
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
}
```

这样就可以实现向上导航的功能了。

问题7：VerticalGridFragment内容为占满整个屏幕。

```
    <!--整个列表距离顶部的距离</item>-->
    <item name="browsePaddingTop">0dp</item>
    <!--控制左右的padding</item>-->
    <item name="browsePaddingStart">0dp</item>
    <item name="browsePaddingEnd">0dp</item>
    <!--第一行距离顶部的距离</item>-->
    <item name="browseRowsMarginTop">0dp</item>
    <item name="browseRowsMarginStart">@dimen/gap_20_dp</item>
```

