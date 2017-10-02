package win.whitelife.ZFThemeLibrary.parse;

/**
 * Created by wuzefeng on 2017/9/29.
 */

public class DrawableValue {

    private String format;

    private String value;

    private int xml;

    private String name;

    public DrawableValue(int xml) {
        this.xml = xml;
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

    public int getXml() {
        return xml;
    }

    public void setXml(int xml) {
        this.xml = xml;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
