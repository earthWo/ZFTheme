package win.whitelife.ZFThemeLibrary.parse;

import android.support.annotation.XmlRes;

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
     * 获取theme
     * @return
     */
    int getXml();
}
