package win.whitelife.ZFThemeLibrary.parse;

import android.support.annotation.XmlRes;

import win.whitelife.ZFThemeLibrary.config.Theme;


/**
 * config信息更新接口
 * Created by wuzefeng on 2017/9/28.
 */

public interface ConfigInterface {

    /**
     * 设置theme
     * @param currentXml
     */
    void setXml(@XmlRes int currentXml);


    /**
     * 设置theme
     * @param themeName
     */
    void setXml( String themeName);


    /**
     * 获取theme
     */
    Theme getTheme();
}
