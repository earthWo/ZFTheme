package win.whitelife.ZFThemeLibrary.parse;

import android.support.annotation.XmlRes;

import java.util.Map;

/**
 * Created by wuzefeng on 2017/9/25.
 */

public interface ThemeXmlParse {

    /**
     * 解析xml
     * @param res
     */
    void inflate(@XmlRes int res);


    /**
     * 获取item值
     * @param name
     * @return
     */
    String getValueByName(String name);


}
