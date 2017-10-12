package whitelife.win.zftheme;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.File;
import win.whitelife.ZFThemeLibrary.ZFTheme;
import win.whitelife.ZFThemeLibrary.config.ThemeType;
import win.whitelife.ZFThemeLibrary.parse.ZipParse;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZFTheme.init(this,"fileTheme");
        setContentView(R.layout.activity_main);
        //解析zip
        ZipParse.getInstance(getApplicationContext()).parseZip("fileTheme",getDataDir().getAbsolutePath()+ File.separator+"fileTheme.zip");
    }


    public void change(View v){
        if(ZFTheme.get().getTheme().type== ThemeType.FILE){
            ZFTheme.get().updateTheme(R.xml.theme1);
        }else if(ZFTheme.get().getTheme().res==R.xml.theme2){
            ZFTheme.get().updateTheme("fileTheme");
        }else if(ZFTheme.get().getTheme().res==R.xml.theme1){
            ZFTheme.get().updateTheme(R.xml.theme2);
        }
    }

    public void jump(View v){
        Intent intent=new Intent(this,SActivity.class);
        startActivity(intent);
    }
}
