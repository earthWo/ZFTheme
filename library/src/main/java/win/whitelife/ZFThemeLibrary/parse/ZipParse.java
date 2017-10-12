package win.whitelife.ZFThemeLibrary.parse;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by wuzefeng on 2017/10/9.
 */

public class ZipParse {

    private Context context;

    private static volatile ZipParse instance;

    private ZipParse(Context context){
        this.context=context;
    }

    public static ZipParse getInstance(Context context){
        if(instance==null){
            synchronized (ZipParse.class){
                if(instance==null){
                    instance=new ZipParse(context);
                }
            }
        }
        return instance;
    }

    /**
     * 解压zip
     * @param themeName theme名字，会创建以该名字命名的文件夹，所有主题的文件会放在里面
     * @param zipPath zip地址
     */
    public void parseZip(String themeName,String zipPath){

        File zipFile=new File(zipPath);
        if(!zipFile.exists()){
            throw new RuntimeException("无法找到文件");
        }

        String parsePath =context.getFilesDir()+File.separator+themeName;
        File parseFile=new File(parsePath);
        createFileDir(parseFile);

        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFile));
            ZipInputStream zis = new ZipInputStream(bis);
            BufferedOutputStream bos = null;

            ZipEntry entry;
            while ((entry=zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                if(entryName.contains("/")){
                    continue;
                }
                File f=new File(parseFile.getAbsolutePath()+File.separator+entryName);
                createFile(f);
                try {
                    bos = new BufferedOutputStream(new FileOutputStream(f));
                }catch (IOException e){}

                int b ;
                while ((b = zis.read()) != -1) {
                    bos.write(b);
                }

                bos.flush();
                bos.close();

            }
            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFileDir(File file){
        if(!file.exists()){
            file.mkdirs();
        }
    }

    private void createFile(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
