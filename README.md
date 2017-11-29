# 通用对话弹窗CommonDialog


项目中,迭代了快8年了,对话框,种类繁多, 难以维护, 有的继承`popwindow`, 有的继承`dialog`, 有的继承`dialogFragment`....而且写法 各不相同.





所以,急需一次版本迭代, 来 优化 系统所有的弹窗. 本着有轮子就不会去 造轮子的原则~ 但是,逛了一圈,开源的 对话弹窗,都不能满足项目需求,

这就是要 造这个了轮子的初衷.


# 效果图

![common_dialog.gif](http://upload-images.jianshu.io/upload_images/1848340-bfd6d41eb4be08cf.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



# 思路

### 1.定义顶级抽象类 `BaseDialog`, 

装饰AlertDialog, 统一dialog样式, 并且用 模板方法模式 进行抽象约束 子类型为.
为了达到 易扩展,代码复用的角度, 将整体dialog,根据 功能区域 布局和加载分为3部分----

- **头部header**: 扩展头部区域,像图片logo头部,带背景,大标题.
- **中间content**: 主内容显示区域,比如输入框,内容详情等等.
- **底部footer**:底部扩展区,主要用来放置 确认按钮等等.



```

    /**
     * 子类必须实现该方法用于显示在界面上的Dialog的Title部分
     *
     * @return title部分的显示的View，返回的titleView为null的话，Dialog将不显示title部分
     */
    protected abstract View initHeader();

    /**
     * 子类必须实现该方法用于显示在界面上的正文部分控件
     * @return content部分显示的内容，如果返回null，将会抛出异常
     */
    protected abstract View initContent();

    /**
     * 子类实现该方法用于显示底部的view
     *
     * @return bottom部分显示的view，如果返回null，bottom部分将不显示
     */
    protected abstract View initBottom();


    /**
     * 模板方法
     */
    protected abstract void loadHeader();

    protected abstract void loadContent();

    protected abstract void loadBottom();


    public   void start(){
        loadHeader();
        loadContent();
        loadBottom();
    };

```


#### 2. 新建子类,CommonNormalDialog CommonImgHeaderDialog CommonLogoHeaderDialog等

子类 分别对模板方法进行实现, 以`CommonNormalDialog`为例

```
/**
     * 普通样式无头部
     * @return
     */
    @Override
    protected View initHeader() {
        return null;
    }

    /**
     * 创建内容布局
     * @return 内容布局
     */
    @Override
    protected View initContent() {

        View content = View.inflate(context, R.layout.common_dialog_content, null);


        tv_title = (TextView) content.findViewById(R.id.tv_title);
        tv_content = (TextView) content.findViewById(R.id.tv_content);

        etv_content = (EditText) content.findViewById(R.id.etv_content);

        v_vertical_line = content.findViewById(R.id.v_vertical_line);
        tv_left = (TextView) content.findViewById(R.id.tv_left);
        tv_right = (TextView) content.findViewById(R.id.tv_right);

        iv_cancel = (ImageView) content.findViewById(R.id.iv_content_cancel);

        iv_cancel.setOnClickListener(this);
        return content;
    }

    /**
     * 创建 底部 按钮布局
     * @return
     */
    @Override
    protected View initBottom() {
        View footer = View.inflate(context, R.layout.common_dialog_footer_btn, null);

        v_vertical_line = footer.findViewById(R.id.v_vertical_line);
        tv_left = (TextView) footer.findViewById(R.id.tv_left);
        tv_right = (TextView) footer.findViewById(R.id.tv_right);

        return footer;

```


### 3.扩展

如果想要扩展, 根据需求相互扩展即可.

 比如 `CommonImgHeaderDialog` 可以选择继承`CommonNormalDialog`.共用`CommonNormalDialog`的 内容布局和 底部布局. 所以,只需要加载header布局即可.


```
/**
 * 通用 带头部背景 样式
 * Created by chenchangjun on 17/10/11.
 */

public class CommonImgHeaderDialog extends CommonNormalDialog implements View.OnClickListener {

    private String url_header;
    private ImageView iv_header_cancel;
    private SimpleDraweeView iv_header;


    @Override
    protected void loadHeader() {


        if (type== DialogConfig.TYPE_HAS_HEADER){
            iv_header.setVisibility(View.VISIBLE);
            iv_header_cancel.setVisibility(View.VISIBLE);


            if (TextUtils.isEmpty(url_header)) {
                iv_header.setVisibility(View.GONE);
            } else {
                //iv_header.setImageURI(url_header);
                iv_header.setBackgroundResource(R.drawable.common_dialog_header_bg);
                Uri uri = Uri.parse(url_header);
                iv_header.setImageURI(uri);
            }

        }
    }

    public CommonImgHeaderDialog setHeaderUrl(String str) {
        url_header = str;
        return this;
    }

    public CommonImgHeaderDialog(Context context) {
        super(context);
    }

    @Override
    protected View initHeader() {
        View header=null;
        if (type==DialogConfig.TYPE_HAS_NO_HEADER){
            return null;
        }
        header = View.inflate(context, R.layout.common_dialog_pic_header, null);
        iv_header_cancel = (ImageView) header.findViewById(R.id.iv_header_cancel);
        iv_header = (SimpleDraweeView) header.findViewById(R.id.iv_header);
        iv_header_cancel.setOnClickListener(this);

        return header;
    }

}


```


