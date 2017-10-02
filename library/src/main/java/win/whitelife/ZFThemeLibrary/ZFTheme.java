package win.whitelife.ZFThemeLibrary;

import android.content.Context;
import android.support.annotation.XmlRes;
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
     * @param drawableClass
     * @param colorClass
     * @param defaultXml
     */
    public static void init(Context context,Class drawableClass,Class colorClass,int defaultXml){
        mInstance=instance(context);
        mInstance.zfThemeConfig.setXml(defaultXml);
        mInstance.zfThemeParse.inflate(defaultXml);
        mInstance.drawableParse.setColorClass(colorClass).setDrawableClass(drawableClass);
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
        if(zfThemeConfig.getXml()!=res){
            zfThemeConfig.setXml(res);
            zfThemeParse.inflate(res);
        }
        zfThemeObserver.updateTheme();
    }

    public void setXml(@XmlRes int currentXml) {
        zfThemeConfig.setXml(currentXml);
    }

    public int getXml() {
        return zfThemeConfig.getXml();
    }


}
