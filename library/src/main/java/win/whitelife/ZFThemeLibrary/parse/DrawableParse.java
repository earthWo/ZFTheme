package win.whitelife.ZFThemeLibrary.parse;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.lang.reflect.Field;

import win.whitelife.ZFThemeLibrary.ZFTheme;


/**
 *
 * Created by wuzefeng on 2017/9/25.
 */

public class DrawableParse implements DrawableInterface{

    private static volatile DrawableParse mInstance;

    private Class drawableClass;

    private Class colorClass;

    private DrawableParse(){}

    public static DrawableParse getInstance(Context mContext){
        if(mInstance==null){
            synchronized (DrawableParse.class){
                if(mInstance==null){
                    mInstance=new DrawableParse();
                }
            }
        }
        return mInstance;
    }


    public boolean needPraiseDrawable(String str){
        return str==null||!str.startsWith("@");
    }


    public DrawableParse setDrawableClass(Class drawableClass) {
        this.drawableClass = drawableClass;
        return this;
    }

    public DrawableParse setColorClass(Class colorClass) {
        this.colorClass = colorClass;
        return this;
    }


    public Drawable parseDrawableFromColorInt(String str){
        ColorDrawable colorDrawable=null;
        try {
            colorDrawable=new ColorDrawable(Color.parseColor(str));
        }catch (Exception e){
            Log.e("颜色解析错误",str);
        }
        return colorDrawable;
    }

    public ColorStateList parseDrawableFromColorStateList(String str){
        ColorStateList colorStateList=null;
        try {
            colorStateList=ColorStateList.valueOf(Color.parseColor(str));
        }catch (Exception e){
            Log.e("颜色解析错误",str);
        }
        return colorStateList;
    }

    public int parseDrawableFromColor(String str){
        return getResByString(str, colorClass);
    }



    public int parseDrawableFromDrawable(String str){
        return getResByString(str, drawableClass);

    }

    private int getResByString(String variableName, Class<?> c){
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
