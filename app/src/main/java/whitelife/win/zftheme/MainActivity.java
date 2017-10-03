package whitelife.win.zftheme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import win.whitelife.ZFThemeLibrary.ZFTheme;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZFTheme.init(this,R.drawable.class,R.color.class,R.xml.theme1);
        setContentView(R.layout.activity_main);
    }


    public void change(View v){
        if(ZFTheme.get().getXml()==R.xml.theme1){
            ZFTheme.get().updateTheme(R.xml.theme2);
        }else{
            ZFTheme.get().updateTheme(R.xml.theme1);
        }
    }

    public void jump(View v){
        Intent intent=new Intent(this,SActivity.class);
        startActivity(intent);
    }
}
