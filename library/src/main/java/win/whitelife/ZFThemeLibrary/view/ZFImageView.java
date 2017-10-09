package win.whitelife.ZFThemeLibrary.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import win.whitelife.ZFThemeLibrary.inteface.BackgroundInterface;
import win.whitelife.ZFThemeLibrary.inteface.ImageInterface;
import win.whitelife.ZFThemeLibrary.parse.ThemeHelper;
import win.whitelife.ZFThemeLibrary.parse.ZFThemeObserver;

/**
 * Created by wuzefeng on 2017/9/24.
 */

public class ZFImageView extends AppCompatImageView implements BackgroundInterface ,ImageInterface{

    private String mBackground;

    private String imageSrc;

    private ZFThemeObserver themeObserver;

    public ZFImageView(Context context) {
        this(context,null);
    }

    public ZFImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZFImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attr(context,attrs);
        backgroundUpdate();
        imageUpdate();
    }


    public void attr(Context context, @Nullable AttributeSet attrs){
        mBackground=ThemeHelper.encodeBackground(context,attrs);
        imageSrc=ThemeHelper.encodeImageSrc(context,attrs);
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
    public void imageUpdate() {
        ThemeHelper.imageParse(getContext().getApplicationContext(),this,imageSrc);
    }
}
