package win.whitelife.ZFThemeLibrary.parse;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import win.whitelife.ZFThemeLibrary.inteface.FormatTypes;

/**
 * Created by wuzefeng on 2017/9/28.
 */

public class ThemeHelper {


    /**
     * 解析并设置背景
     * @param context
     * @param view
     * @param mBackground
     */
    public static <T extends View>void backgroundParse(Context context,T view,String mBackground){
        if(TextUtils.isEmpty(mBackground)){
            return;
        }
        DrawableValue drawableValue= ZFThemeXmlParse.getInstance(context).parseDrawable(mBackground);
        if(FormatTypes.COLOR_INT.equals(drawableValue.getFormat())){
            drawBackground(view, DrawableParse.getInstance(context).parseDrawableFromColorInt(drawableValue.getValue()));
        }else if(FormatTypes.DRAWABLE.equals(drawableValue.getFormat())){
            drawBackgroundRes(view, DrawableParse.getInstance(context).parseDrawableFromDrawable(drawableValue.getValue()));
        }else if(FormatTypes.COLOR.equals(drawableValue.getFormat())){
            drawBackgroundRes(view, DrawableParse.getInstance(context).parseDrawableFromColor(drawableValue.getValue()));
        }
    }


    /**
     * 解析并设置图片
     * @param context
     * @param imageView
     * @param image
     */
    public static <T extends ImageView> void imageParse(Context context, T imageView, String image){
        if(TextUtils.isEmpty(image)){
            return;
        }
        DrawableValue drawableValue= ZFThemeXmlParse.getInstance(context).parseDrawable(image);
        if(FormatTypes.COLOR_INT.equals(drawableValue.getFormat())){
            drawImage(imageView, DrawableParse.getInstance(context).parseDrawableFromColorInt(drawableValue.getValue()));
        }else if(FormatTypes.DRAWABLE.equals(drawableValue.getFormat())){
            drawImageRes(imageView, DrawableParse.getInstance(context).parseDrawableFromDrawable(drawableValue.getValue()));
        }else if(FormatTypes.COLOR.equals(drawableValue.getFormat())){
            drawImageRes(imageView, DrawableParse.getInstance(context).parseDrawableFromColor(drawableValue.getValue()));
        }
    }


    public static <T extends TextView> void textColorParse(Context context, T textView, String color){
        if(TextUtils.isEmpty(color)){
            return;
        }
        DrawableValue drawableValue= ZFThemeXmlParse.getInstance(context).parseDrawable(color);
        if(FormatTypes.COLOR_INT.equals(drawableValue.getFormat())){
            drawTextView(textView, DrawableParse.getInstance(context).parseDrawableFromColorStateList(drawableValue.getValue()));
        }else if(FormatTypes.COLOR.equals(drawableValue.getFormat())){
            drawTextViewRes(textView, DrawableParse.getInstance(context).parseDrawableFromColor(drawableValue.getValue()));
        }
    }

    public static <T extends TextView> void textHintColorParse(Context context, T textView, String color){
        if(TextUtils.isEmpty(color)){
            return;
        }
        DrawableValue drawableValue= ZFThemeXmlParse.getInstance(context).parseDrawable(color);
        if(FormatTypes.COLOR_INT.equals(drawableValue.getFormat())){
            drawHintTextView(textView, DrawableParse.getInstance(context).parseDrawableFromColorStateList(drawableValue.getValue()));
        }else if(FormatTypes.COLOR.equals(drawableValue.getFormat())){
            drawHintTextViewRes(textView, DrawableParse.getInstance(context).parseDrawableFromColor(drawableValue.getValue()));
        }
    }

    public static <T extends View>void drawBackground(T view,Drawable drawable){
        if(view!=null&&drawable!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackground(drawable);
            }else{
                view.setBackgroundDrawable(drawable);
            }
            drawable.setCallback(null);
        }
    }

    public static <T extends View>void drawBackgroundRes(T view,int res){
        if(view!=null&&res!=0){
            view.setBackgroundResource(res);
        }
    }


    public static <T extends ImageView>void drawImage(T view,Drawable drawable){
        if(view!=null&&drawable!=null){
            view.setImageDrawable(drawable);
            drawable.setCallback(null);
        }
    }

    public static  <T extends ImageView>void drawImageRes(T view,int res){
        if(view!=null&&res!=0){
            view.setBackgroundResource(res);
        }
    }

    public static <T extends TextView>void drawTextView(T view,ColorStateList color){
        if(view!=null&&color!=null){
            view.setTextColor(color);
        }
    }

    public static  <T extends TextView>void drawTextViewRes(T view,int color){
        if(view!=null&&color!=0){
            view.setTextColor(color);
        }
    }

    public static <T extends TextView>void drawHintTextView(T view,ColorStateList color){
        if(view!=null&&color!=null){
            view.setHintTextColor(color);
        }
    }

    public static  <T extends TextView>void drawHintTextViewRes(T view, int color){
        if(view!=null&&color!=0){
            view.setHintTextColor(color);
        }
    }

}
