# 项目链接 [GitHub 喜欢请给小星星](https://github.com/XiaoBaiCZ/RecyclerViewExtend/)
### 项目目标
- 提高RecycleView的**使用效率**
- 无需写Adapter模板
- 只需关注**数据源 & 视图绑定**

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

### 导入
~~~
//root -> build.gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
~~~
~~~
//project -> build.gradle
dependencies {
    implementation 'com.github.XiaoBaiCZ:RecyclerViewExtend:v0.3'
}
~~~

### 效果图
![list1.png](https://upload-images.jianshu.io/upload_images/4191132-ad053b8c9f96acff.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 使用示例
[基本使用 Demo （多类型）](https://github.com/XiaoBaiCZ/RecyclerViewExtend/blob/master/demo/src/main/java/cc/xiaobaicz/demo/MainActivity.kt)

[配合ViewBinding使用 Demo](https://github.com/XiaoBaiCZ/RecyclerViewExtend/tree/master/demo-viewbinding/src/main/java/cc/xiaobaicz/demo_viewbinding)

[配合DataBinding使用 Demo](https://github.com/XiaoBaiCZ/RecyclerViewExtend/tree/master/demo-databinding/src/main/java/cc/xiaobaicz/demo_databinding)

### 如喜欢且很有帮助可以请作者吃冰棒哦~~~
![zfb.jpg](https://upload-images.jianshu.io/upload_images/4191132-ba6603f3825d069f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

