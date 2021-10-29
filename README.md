# 项目链接 [GitHub 喜欢请给小星星](https://github.com/XiaoBaiCZ/RecyclerViewExtend/)
### 项目目标
- 提高RecycleView的**使用效率**
- 无需写Adapter模板
- 只需关注**数据源 & 视图绑定**
- 化头/尾部试图的添加
- 多列表拼接

### [Log](https://github.com/XiaoBaiCZ/RecyclerViewExtend/tree/master/log)

### 介绍
> **RecyclerView的扩展，扩展了通用Adapter，通用ViewHolder，统一的配置入口，无需再写Adapter，只需关注数据&视图绑定**
> 
> **无需书写适配器Adapter，通过config函数传入 数据源 & 布局管理器（默认线性布局）**
> 
> **再通过addType函数添加 数据类 对应 的 class，布局id，视图绑定函数 即可**
> 
> **多布局情况只需多次调用addType进行添加即可**
> 
> **支持自定义ViewHolder**
> 
> **支持ViewBinding，DataBinding，KT-Extensions等工具，通过自定义ViewHolder**
>
> **支持 Header, Foot 视图添加，具体可查看Demo调用相当简单**
>
> **支持多列表拼接**

### 使用示例 （Demo有注释）
[基本使用 Demo （多类型）](https://github.com/XiaoBaiCZ/RecyclerViewExtend/blob/master/demo/src/main/java/cc/xiaobaicz/demo/MainActivity.kt)

[配合ViewBinding使用 Demo](https://github.com/XiaoBaiCZ/RecyclerViewExtend/tree/master/demo-viewbinding/src/main/java/cc/xiaobaicz/demo_viewbinding)

[配合DataBinding使用 Demo](https://github.com/XiaoBaiCZ/RecyclerViewExtend/tree/master/demo-databinding/src/main/java/cc/xiaobaicz/demo_databinding)

### 导入 (需Kotlin1.4以上，因使用了SAM接口）
~~~ gradle
//root -> build.gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
~~~
~~~ gradle
//project -> build.gradle
dependencies {
    implementation 'com.github.XiaoBaiCZ:RecyclerViewExtend:0.6.1'
}
~~~

### API使用介绍
~~~ kotlin
/**
 * 配置入口
 * @param data 数据源：任意类型，支持混合类型
 * @param lm 布局管理器：默认线性布局
 * @param config 类型建造者函数，通过 addType 添加 类型对应布局 和 视图绑定函数
 */
fun RecyclerView.config(
    data: MutableList,
    lm: RecyclerView.LayoutManager = LinearLayoutManager(context),
    config: Builder.()->Unit
)


//Builder类
//自定义ViewHolder
/**
 * 添加数据/视图类型函数
 * @param resId 布局ID
 * @param func 视图绑定函数
 */
fun <D, V> addType(resId: Int, func: BindFunc)


//默认ViewHolderX
/**
 * 添加数据/视图类型函数
 * @param resId 布局ID
 * @param func 视图绑定函数
 */
fun <D> addType(resId: Int, func: BindFunc)
~~~

### 使用流程
~~~ kotlin
val data: ArrayList<Any>()      //数据源
val data2: ArrayList<Any>()      //拼接数据源
val list: RecyclerView = ...    //RecyclerView 实例

//调用配置函数，传入数据源（可接收多类型，通过Any），默认线性布局
list.config(data) {

    //你的 数据类型 自动跟 布局ID 绑定
    addType<你的数据类型1, 你的自定义ViewHolder1>(你的布局ID1) { d, h, p ->   //d 数据，h ViewHolder， p 下标
        //这里执行视图的数据绑定
    }
    
    //可多次调用addType来进行多布局适配
    addType<你的数据类型2, 你的自定义ViewHolder2>(你的布局ID2) { d, h, p ->   //d 数据，h ViewHolder， p 下标
        //这里执行视图的数据绑定
    }
    //添加头部
    addHeader<Header1>(Header1(), R.layout.item_header1)
    addHeader<Header2>(Header2(), R.layout.item_header2)
    //添加尾部
    addFoot<Foot1>(Foot1(), R.layout.item_foot1)
    ddFoot<Foot2>(Foot2(), R.layout.item_foot2)
    //拼接列表，第一种方式
    concatContent(data2)
    
}

//配置布局管理器
val lm: GridLayoutManager   //其他布局管理器
list.config(data, lm) {
    ...
}
~~~

### 效果图
![list1.png](https://upload-images.jianshu.io/upload_images/4191132-ad053b8c9f96acff.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 如喜欢且很有帮助可以请作者吃冰棒哦~~~
![zfb.jpg](https://upload-images.jianshu.io/upload_images/4191132-ba6603f3825d069f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

