# ZFTheme
android主题工具

ZFTheme是一款是android换主题框架。目前支持替换xml实现换肤，支持color和drawable。

### 效果图：

![](http://7xjrms.com1.z0.glb.clouddn.com/SM-G9500_20171003143102_1.gif)

### 支持四种属性变化
zf_background:设置背景
zf_image:设置image src
zf_text_color:设置text color 颜色
zf_text_hintColor:设置 texthint 颜色
### 使用方法：

#### 导入方法

Gradle:

```compile 'win.whitelife.ZFThemeLibrary:library:1.1.0'```

Maven:

```
<dependency>
  <groupId>win.whitelife.ZFThemeLibrary</groupId>
  <artifactId>library</artifactId>
  <version>1.1.0</version>
  <type>pom</type>
</dependency>
```

#### 初始化
ZFTheme.init(this,R.xml.theme1);
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
    <item name="background" format="colorInt">#456789</item>//直接使用颜色
    <item name="image" format="drawable">a</item>//使用drawable资源
    <item name="textcolor" format="color">colorPrimary</item>//使用color资源
    <item name="file_background" format="flie">logo</item>//使用文件
</theme>
```
#### 切换主题

```
ZFTheme.get().updateTheme(R.xml.theme2);
```

#### 支持使用文件更新theme

##### 压缩文件示例：

![](http://7xjrms.com1.z0.glb.clouddn.com/QQ20171012-204104@2x.png)

将文件压缩成zip包（xml文件名字必须是theme，所有的文件的名字不能出现 '/' ）。

##### 解析压缩包：

```
ZipParse.getInstance(getApplicationContext()).parseZip("fileTheme",zipFilePath);//第一个参数为theme名字，第二个为zip包地址
```

##### 切换主题

```
ZFTheme.get().updateTheme("fileTheme");
//初始化时使用压缩包文件
ZFTheme.init(this,"fileTheme");
```

ps：考虑到一半情况下，项目中都自带下载框架，所以本库不包含下载zip的相关功能，需要自行实现，解析后的zip可以删除。 

#### 自定义View

自定义View如果需要变化背景，则需要实现`BackgroundInterface`接口。在该View中，需要解析设置的背景颜色：

```
mBackground=ThemeHelper.encodeBackground(context,attrs);
```

同时实现`backgroundUpdate`方法。

```
ThemeHelper.backgroundParse(getContext().getApplicationContext(),this,mBackground);
```

在View显示和消失时需要注册该View：

```
 @Override
    public void register() {
        themeObserver.registerView(this);
    }

    @Override
    public void unRegister() {
        themeObserver.unRegisterView(this);
    }
```

提供的实现接口：

`BackgroundInterface`：背景变化

`ImageInterface`:图片变化

`TextColorInterface`:文字颜色变化

`TextColorHintInterface`:文字的hint颜色变化
