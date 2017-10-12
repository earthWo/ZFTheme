package win.whitelife.ZFThemeLibrary.config;

import android.support.annotation.XmlRes;

/**
 * Created by wuzefeng on 2017/10/12.
 */

public class Theme {

    @win.whitelife.ZFThemeLibrary.config.ThemeType
    public int type;

    @XmlRes
    public int res;

    public String themeName;

}
