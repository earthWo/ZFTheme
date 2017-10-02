package win.whitelife.ZFThemeLibrary.parse;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

import win.whitelife.ZFThemeLibrary.inteface.BackgroundInterface;
import win.whitelife.ZFThemeLibrary.inteface.ImageInterface;
import win.whitelife.ZFThemeLibrary.inteface.TextColorHintInterface;
import win.whitelife.ZFThemeLibrary.inteface.TextColorInterface;

/**
 * Created by wuzefeng on 2017/9/27.
 */

public class ZFThemeObserver implements ObserverInterface {

    private LinkedHashMap<Integer,View> mZFViews;

    private static ZFThemeObserver instance;

    private Context context;

    private ZFThemeObserver(Context context){
        mZFViews=new LinkedHashMap<>();
        this.context=context;
    }


    public static ZFThemeObserver getInstance(Context context){
        if(instance==null){
            synchronized (ZFThemeObserver.class){
                if(instance==null){
                    instance=new ZFThemeObserver(context);
                }
            }
        }
        return instance;
    }


    public void registerView(View view){
        mZFViews.put(view.hashCode(),view);
    }


    public void unRegisterView(View view){
        mZFViews.remove(view.hashCode());
    }


    public void updateTheme(){
        ListIterator<Map.Entry<Integer, View>>l=new ArrayList<>(mZFViews.entrySet()).listIterator(mZFViews.size());
        while(l.hasPrevious()){
            int code=l.previous().getKey();
            View view=mZFViews.get(code);
            if(view!=null){
                if(view instanceof BackgroundInterface){
                    ((BackgroundInterface)view).backgroundUpdate();
                }
                if(view instanceof ImageInterface){
                    ((ImageInterface)view).imageUpdate();
                }
                if(view instanceof TextColorInterface){
                    ((TextColorInterface)view).textColorUpdate();
                }
                if(view instanceof TextColorHintInterface){
                    ((TextColorHintInterface)view).textHintColorUpdate();
                }
            }else{
                continue;
            }
        }
    }


}
