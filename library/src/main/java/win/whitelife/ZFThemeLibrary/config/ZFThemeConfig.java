package win.whitelife.ZFThemeLibrary.config;

import android.support.annotation.XmlRes;

import win.whitelife.ZFThemeLibrary.parse.ConfigInterface;

/**
 * config 配置类
 * Created by wuzefeng on 2017/9/25.
 */

public class ZFThemeConfig implements ConfigInterface {

    private ZFThemeConfig(){}

    private static volatile ZFThemeConfig mInstance;

    private int currentXml;


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
        this.currentXml = defaultXml;
    }

    public void setXml(@XmlRes int currentXml){
        this.currentXml = currentXml;
    }

    @Override
    public int getXml() {
        return currentXml;
    }

}
