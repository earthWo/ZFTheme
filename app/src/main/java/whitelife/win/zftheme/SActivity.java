package whitelife.win.zftheme;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import win.whitelife.ZFThemeLibrary.ZFTheme;

/**
 * Created by wuzefeng on 2017/9/28.
 */

public class SActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
    }

    public void update(View v){
        if(ZFTheme.get().getXml()==R.xml.theme1){
            ZFTheme.get().updateTheme(R.xml.theme2);
        }else{
            ZFTheme.get().updateTheme(R.xml.theme1);
        }
    }
}
