package win.whitelife.ZFThemeLibrary.config;

import android.support.annotation.XmlRes;

import win.whitelife.ZFThemeLibrary.parse.ConfigInterface;

/**
 * config 配置类
 * Created by wuzefeng on 2017/9/25.
 */

public class ZFThemeConfig implements ConfigInterface {

    private ZFThemeConfig(){
        theme=new Theme();
    }

    private static volatile ZFThemeConfig mInstance;


    private Theme theme;


    public static ZFThemeConfig getInstance(){
        if(mInstance==null){
            synchronized (ZFThemeConfig.class){
                if(mInstance==null){
                    mInstance=new ZFThemeConfig();
                }
            }
        }
        return mInstance;
    }

    public void setDefaultXml(@XmlRes int defaultXml) {
        theme.res=defaultXml;
        theme.type= win.whitelife.ZFThemeLibrary.config.ThemeType.RESOURSES;
    }

    public void setXml(@XmlRes int currentXml){
        theme.res=currentXml;
        theme.type= win.whitelife.ZFThemeLibrary.config.ThemeType.RESOURSES;
    }


    @Override
    public void setXml(String themeName) {
        theme.themeName=themeName;
        theme.type= win.whitelife.ZFThemeLibrary.config.ThemeType.FILE;
    }

    @Override
    public Theme getTheme() {
        return theme;
    }


}
