package win.whitelife.ZFThemeLibrary.inteface;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * view注册接口
 * Created by wuzefeng on 2017/9/24.
 */

public interface ViewInterface {

    /**
     * 解析配置的参数
     * @param context
     * @param attrs
     */
    void attr(Context context, @Nullable AttributeSet attrs);

    /**
     * 注册view
     */
    void register();

    /**
     * 注销view
     */
    void unRegister();

}
