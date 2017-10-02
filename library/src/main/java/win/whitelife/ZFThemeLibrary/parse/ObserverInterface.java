package win.whitelife.ZFThemeLibrary.parse;

import android.support.annotation.XmlRes;
import android.view.View;

/**
 * Created by wuzefeng on 2017/9/28.
 */

public interface ObserverInterface {

    /**
     * 注册view
     * @param view
     */
    void registerView(View view);

    /**
     * 删除view
     * @param view
     */
    void unRegisterView(View view);

    /**
     * 更新theme
     */
    void updateTheme();


}
