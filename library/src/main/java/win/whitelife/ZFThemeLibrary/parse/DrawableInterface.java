package win.whitelife.ZFThemeLibrary.parse;

import android.graphics.drawable.Drawable;

/**
 * 图片或颜色解析接口
 * Created by wuzefeng on 2017/9/28.
 */

public interface DrawableInterface {

    /**
     * 解析colordrawable
     * @param str
     * @return
     */
    Drawable parseDrawableFromColorInt(String str);

    /**
     * 设置drawable class
     * @param drawableClass
     * @return
     */
    DrawableParse setDrawableClass(Class drawableClass);

    /**
     * 设置color文件
     * @param colorClass
     * @return
     */
    DrawableParse setColorClass(Class colorClass);

    /**
     * 解析color文件
     * @param str
     * @return
     */
    int parseDrawableFromColor(String str);


    /**
     * 解析图片文件
     * @param str
     * @return
     */
    int parseDrawableFromDrawable(String str);


}
