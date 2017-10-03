# ZFTheme
android主题工具

ZFTheme是一款是android换主题框架。目前支持替换xml实现换肤，支持color和drawable。

### 效果图：

![](http://7xjrms.com1.z0.glb.clouddn.com/SM-G9500_20171003143102_1.gif)

### 四种方式
zf_background:设置背景
zf_image:设置image src
zf_text_color:设置text 颜色
zf_text_hintColor:设置 texthint 颜色
### 使用方法：

#### 导入方法

Gradle:

```compile 'win.whitelife.ZFThemeLibrary:library:1.0'```

Maven:

```
<dependency>
  <groupId>win.whitelife.ZFThemeLibrary</groupId>
  <artifactId>library</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```

#### 初始化
ZFTheme.init(this,R.drawable.class,R.color.class,R.xml.theme1);
设置drawable的class，color的class，设置初始的xml。
#### layout文件配置
```
<win.whitelife.ZFThemeLibrary.view.ZFView
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:zf_background="background"
         />


    <win.whitelife.ZFThemeLibrary.view.ZFImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:zf_image="image"
        />
```
#### xml设置
```
<theme>
    <item name="background" format="colorInt">#456789</item>
    <item name="image" format="drawable">a</item>
    <item name="textcolor" format="colorInt">#990000</item>
</theme>
```
#### 切换主题
```
ZFTheme.get().updateTheme(R.xml.theme2);
```
