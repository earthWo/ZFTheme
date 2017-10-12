package win.whitelife.ZFThemeLibrary.parse;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.File;
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

    private Context context;

    private DrawableParse(Context context){
        this.context=context;
    }

    public static DrawableParse getInstance(Context mContext){
        if(mInstance==null){
            synchronized (DrawableParse.class){
                if(mInstance==null){
                    mInstance=new DrawableParse(mContext);
                }
            }
        }
        return mInstance;
    }


    public DrawableParse setDrawableClass(Class drawableClass) {
        this.drawableClass = drawableClass;
        return this;
    }

    public DrawableParse setColorClass(Class colorClass) {
        this.colorClass = colorClass;
        return this;
    }


    @Override
    public Drawable parseDrawableColor(String str) {
        ColorDrawable colorDrawable=null;
        try {
            colorDrawable=new ColorDrawable();
        }catch (Exception e){
            Log.e("颜色解析错误",str);
        }
        return colorDrawable;
    }

    public int parseDrawableFromColorInt(String str){
        try {
           return Color.parseColor(str);
        }catch (Exception e){
            Log.e("颜色解析错误",str);
        }
        return 0;
    }

    @Override
    public Drawable parseDrawableFile(String str) {
        File file=new File(context.getFilesDir()+File.separator+ZFTheme.get().getTheme().themeName+File.separator+str);
        if(file.exists()){
            return BitmapDrawable.createFromPath(file.getAbsolutePath());
        }
        return null;
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
