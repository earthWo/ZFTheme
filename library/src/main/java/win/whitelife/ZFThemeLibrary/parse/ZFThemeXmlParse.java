package win.whitelife.ZFThemeLibrary.parse;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.annotation.XmlRes;
import android.util.Xml;
import android.view.InflateException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import win.whitelife.ZFThemeLibrary.ZFTheme;


/**
 * Created by wuzefeng on 2017/7/12.
 */

public class ZFThemeXmlParse implements ThemeXmlParse {

    /** Menu tag name in XML. */
    private static final String XML_THEME = "theme";

    /** Item tag name in XML. */
    private static final String XML_ITEM = "item";


    private static final String ITEM_NAME="name";

    private static final String ITEM_FORMAT="format";


    private Context context;

    private ZFThemeXmlParse(Context context) {
        this.context = context;
        this.mItemValues=new HashMap<>();
        this.mItemFormats=new HashMap<>();
    }


    private static volatile ZFThemeXmlParse mZFThemeParse;

    private Map<String,String>mItemValues;

    private Map<String,String>mItemFormats;




    public static ZFThemeXmlParse getInstance(Context context){
        if(mZFThemeParse==null){
            synchronized (ZFThemeXmlParse.class){
                if(mZFThemeParse==null){
                    mZFThemeParse=new ZFThemeXmlParse(context);
                }
            }
        }
        return mZFThemeParse;
    }



    private void addItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        String name = null;
        String format = null;
        for(int i=0;i<parser.getAttributeCount();i++){
            if(ITEM_NAME.equals(parser.getAttributeName(i))){
                name=parser.getAttributeValue(i);
            }else if(ITEM_FORMAT.equals(parser.getAttributeName(i))){
                format=parser.getAttributeValue(i);
            }
            if(name!=null&&format!=null){
                String value=parser.nextText();
                mItemValues.put(name,value);
                mItemFormats.put(name,format);
                break;
            }
        }
    }



    @Override
    public void inflate(@XmlRes int res) {
        if(res!=0&&context!=null){
            XmlResourceParser parser = null;
            try {
                parser = context.getResources().getXml(res);
                mItemValues.clear();
                parseMenu(parser);
            } catch (XmlPullParserException e) {
                throw new InflateException("theme xml文件错误", e);
            } catch (IOException e) {
                throw new InflateException("theme xml文件错误", e);
            } finally {
                if (parser != null) parser.close();
            }
        }
    }

    @Override
    public void inflate(String themeName) {
        if(themeName!=null&&context!=null){
            XmlPullParser parser = Xml.newPullParser();
            try {

                File file=new File(context.getFilesDir()+File.separator+themeName+File.separator+"theme.xml");

                if(!file.exists()){
                    throw new FileNotFoundException("无法找到xml文件");
                }
                InputStream in = new FileInputStream(file);

                //设置xmlpullparser的一些参数
                parser.setInput(in, "utf-8");
                mItemValues.clear();
                parseMenu(parser);
            } catch (XmlPullParserException e) {
                throw new InflateException("theme xml文件错误", e);
            } catch (IOException e) {
                throw new InflateException("theme xml文件错误", e);
            }
        }
    }

    @Override
    public String getValueByName(String name) {
        if(mItemValues.containsKey(name)){
            return mItemValues.get(name);
        }
        return null;
    }


    private void parseMenu(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        String tagName="";
        //遍历xml
        do{
            if (eventType == XmlPullParser.START_TAG) {
                tagName = parser.getName();
                if (tagName.equals(XML_THEME)) {
                    // Go to next tag
                    eventType = parser.next();
                    break;
                }
                throw new RuntimeException("theme解析错误");
            }
            eventType=parser.next();
        }while(eventType!= XmlPullParser.END_DOCUMENT);

        boolean reachedEndOfMenu = false;
        while(!reachedEndOfMenu){
            tagName = parser.getName();
            switch (eventType){
                case XmlPullParser.END_DOCUMENT:
                    throw new RuntimeException("xml错误");
                case XmlPullParser.END_TAG:
                    if (tagName.equals(XML_THEME)) {
                        reachedEndOfMenu = true;
                    }
                    break;
                case XmlPullParser.START_TAG:
                    if (tagName.equals(XML_ITEM)) {
                        addItem(parser);
                    }
                    break;
            }
            eventType = parser.next();
        }
    }



    public DrawableValue parseDrawable(String name){
        DrawableValue drawableValue=new DrawableValue(ZFTheme.get().getTheme());
        drawableValue.setName(name);
        drawableValue.setFormat(mItemFormats.get(name));
        drawableValue.setValue(mItemValues.get(name));
        return drawableValue;
    }
}
