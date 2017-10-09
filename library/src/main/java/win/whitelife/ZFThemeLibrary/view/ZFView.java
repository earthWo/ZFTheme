package win.whitelife.ZFThemeLibrary.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import win.whitelife.ZFThemeLibrary.parse.ZFThemeObserver;
import win.whitelife.ZFThemeLibrary.inteface.BackgroundInterface;
import win.whitelife.ZFThemeLibrary.parse.ThemeHelper;

/**
 * Created by wuzefeng on 2017/9/24.
 */

public class ZFView extends View implements BackgroundInterface {

    private String mBackground;

    private ZFThemeObserver themeObserver;

    public ZFView(Context context) {
        this(context,null);
    }

    public ZFView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZFView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackground=ThemeHelper.encodeBackground(context,attrs);
        themeObserver=ZFThemeObserver.getInstance(context.getApplicationContext());
        backgroundUpdate();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unRegister();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        register();
    }

    @Override
    public void backgroundUpdate() {
        ThemeHelper.backgroundParse(getContext().getApplicationContext(),this,mBackground);
    }

    @Override
    public void register() {
        themeObserver.registerView(this);
    }

    @Override
    public void unRegister() {
        themeObserver.unRegisterView(this);
    }

}
