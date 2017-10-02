package win.whitelife.ZFThemeLibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import win.whitelife.ZFThemeLibrary.R;
import win.whitelife.ZFThemeLibrary.inteface.BackgroundInterface;
import win.whitelife.ZFThemeLibrary.parse.ThemeHelper;
import win.whitelife.ZFThemeLibrary.parse.ZFThemeObserver;

/**
 * Created by wuzefeng on 2017/9/24.
 */

public class ZFButtonView extends AppCompatButton implements BackgroundInterface {

    private String mBackground;

    private ZFThemeObserver themeObserver;

    public ZFButtonView(Context context) {
        this(context,null);
    }

    public ZFButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZFButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attr(context,attrs);
        backgroundUpdate();
    }


    public void attr(Context context, @Nullable AttributeSet attrs){
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.ZFThemeView);
        if(a.hasValue(R.styleable.ZFThemeView_zf_background)){
            mBackground=a.getString(R.styleable.ZFThemeView_zf_background);
        }
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

}
