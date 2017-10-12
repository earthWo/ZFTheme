package win.whitelife.ZFThemeLibrary.parse;

import win.whitelife.ZFThemeLibrary.config.Theme;

/**
 * Created by wuzefeng on 2017/9/29.
 */

public class DrawableValue {

    private String format;

    private String value;

    private Theme theme;

    private String name;

    public DrawableValue(Theme theme) {
        this.theme = theme;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
