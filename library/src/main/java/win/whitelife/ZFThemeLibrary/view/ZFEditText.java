package win.whitelife.ZFThemeLibrary.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import win.whitelife.ZFThemeLibrary.inteface.BackgroundInterface;
import win.whitelife.ZFThemeLibrary.inteface.TextColorHintInterface;
import win.whitelife.ZFThemeLibrary.inteface.TextColorInterface;
import win.whitelife.ZFThemeLibrary.parse.ThemeHelper;
import win.whitelife.ZFThemeLibrary.parse.ZFThemeObserver;

/**
 * Created by wuzefeng on 2017/9/24.
 */

public class ZFEditText extends android.support.v7.widget.AppCompatEditText implements BackgroundInterface ,TextColorInterface
        ,TextColorHintInterface{

    private String mBackground;

    private String textColorSrc;

    private String textHintColorSrc;

    private ZFThemeObserver themeObserver;

    public ZFEditText(Context context) {
        this(context,null);
    }

    public ZFEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZFEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attr(context,attrs);
        backgroundUpdate();
        textColorUpdate();
        textHintColorUpdate();
    }


    public void attr(Context context, @Nullable AttributeSet attrs){
        mBackground=ThemeHelper.encodeBackground(context,attrs);
        textColorSrc=ThemeHelper.encodeTextColor(context,attrs);
        textHintColorSrc=ThemeHelper.encodeTextHintColor(context,attrs);
        themeObserver=ZFThemeObserver.getInstance(context.getApplicationContext());
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


    @Override
    public void textColorUpdate() {
        ThemeHelper.textColorParse(getContext().getApplicationContext(),this,textColorSrc);
    }

    @Override
    public void textHintColorUpdate() {
        ThemeHelper.textHintColorParse(getContext().getApplicationContext(),this,textHintColorSrc);
    }
}
