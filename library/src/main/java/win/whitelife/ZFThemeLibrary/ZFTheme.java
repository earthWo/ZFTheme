package win.whitelife.ZFThemeLibrary;

import android.content.Context;
import android.support.annotation.XmlRes;

import win.whitelife.ZFThemeLibrary.config.Theme;
import win.whitelife.ZFThemeLibrary.parse.ZFThemeObserver;
import win.whitelife.ZFThemeLibrary.config.ZFThemeConfig;
import win.whitelife.ZFThemeLibrary.parse.ConfigInterface;
import win.whitelife.ZFThemeLibrary.parse.DrawableInterface;
import win.whitelife.ZFThemeLibrary.parse.ObserverInterface;
import win.whitelife.ZFThemeLibrary.parse.ThemeXmlParse;
import win.whitelife.ZFThemeLibrary.parse.DrawableParse;
import win.whitelife.ZFThemeLibrary.parse.ZFThemeXmlParse;

/**
 * ZFTheme入口类
 * Created by wuzefeng on 2017/9/26.
 */

public class ZFTheme {

    private static volatile ZFTheme mInstance;

    private ConfigInterface zfThemeConfig;

    private ThemeXmlParse zfThemeParse;

    private DrawableInterface drawableParse;

    private ObserverInterface zfThemeObserver;



    private ZFTheme(Context context){
        zfThemeConfig=ZFThemeConfig.getInstance();
        zfThemeParse= ZFThemeXmlParse.getInstance(context);
        drawableParse=DrawableParse.getInstance(context);
        zfThemeObserver=ZFThemeObserver.getInstance(context);
    }

    /**
     * 初始化所有的类
     * @param context
     * @param defaultXml
     */
    public static void init(Context context,int defaultXml){
        mInstance=instance(context);
        mInstance.zfThemeConfig.setXml(defaultXml);
        mInstance.zfThemeParse.inflate(defaultXml);
        try {
            Class<?> clazzDrawable = Class.forName(context.getPackageName() + ".R$drawable");
            Class<?> clazzColor = Class.forName(context.getPackageName() + ".R$color");
            mInstance.drawableParse.setColorClass(clazzColor).setDrawableClass(clazzDrawable);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void init(Context context,String themeName){
        mInstance=instance(context);
        mInstance.zfThemeConfig.setXml(themeName);
        mInstance.zfThemeParse.inflate(themeName);
        try {
            Class<?> clazzDrawable = Class.forName(context.getPackageName() + ".R$drawable");
            Class<?> clazzColor = Class.forName(context.getPackageName() + ".R$color");
            mInstance.drawableParse.setColorClass(clazzColor).setDrawableClass(clazzDrawable);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ZFTheme get(){
        return mInstance;
    }


    private static ZFTheme instance(Context context){
        if(mInstance==null){
            synchronized (ZFTheme.class){
                if(mInstance==null){
                    mInstance=new ZFTheme(context);
                }
            }
        }
        return mInstance;
    }


    public void updateTheme(@XmlRes int res) {
        if(res!=0){
            zfThemeConfig.setXml(res);
            zfThemeParse.inflate(res);
        }
        zfThemeObserver.updateTheme();
    }

    public void updateTheme(String themeName) {
        if(themeName!=null){
            zfThemeConfig.setXml(themeName);
            zfThemeParse.inflate(themeName);
        }
        zfThemeObserver.updateTheme();
    }

    public void setXml(@XmlRes int currentXml) {
        zfThemeConfig.setXml(currentXml);
    }

    public Theme getTheme() {
        return zfThemeConfig.getTheme();

    }

}
