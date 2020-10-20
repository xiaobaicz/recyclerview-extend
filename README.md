# 项目链接 [GitHub 喜欢请给小星星](https://github.com/XiaoBaiCZ/RecyclerViewExtend/)
### 项目目标
- 提高RecycleView的**使用效率**
- 无需写Adapter模板
- 只需关注**数据源 & 视图绑定**

### [Log](https://github.com/XiaoBaiCZ/RecyclerViewExtend/tree/master/log)

### 介绍
RecyclerView的扩展，扩展了通用Adapter，通用ViewHolder，统一的配置入口，无需再写Adapter，只需关注数据&视图绑定

### 效果图
![list1.png](https://upload-images.jianshu.io/upload_images/4191132-ad053b8c9f96acff.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 用法介绍
> **无需书写适配器Adapter，通过config函数传入 数据源 & 布局管理器（默认线性布局）**
> **再通过addType函数添加 数据类 对应 的 class，布局id，视图绑定函数 即可**
> **多布局情况只需多次调用addType进行添加即可**
> **支持自定义ViewHolder**
> **支持ViewBinding，DataBinding，KT-Extensions等工具，通过自定义ViewHolder**

### 使用示例
~~~ Kotlin
//item数据类型
data class Head(var img: Int)
data class User1(var name: String, var age: Int)
data class User2(var name: String, var age: Int)

//ViewBinding，DataBinding同理
//KT-Extensions 自定义ViewHolder
class KTViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name2: TextView
    val age2: TextView
    init {
        name2 = view.name2
        age2 = view.age2
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //创建数据集
        val data = ArrayList<Any>()
        data.add(Head(R.mipmap.ic_launcher))
        //测试数据
        for (i in 1..20) {
            if (i and 1 == 0) {
                data.add(User1("user$i", 18 + i))
            } else {
                data.add(User2("user$i", 18 + i))
            }
        }

        //无需书写适配器Adapter，通过config函数传入 数据源 & 布局管理器（默认线性布局）
        //再通过addType函数添加 数据类 对应 的 class，布局id，视图绑定函数 即可
        //多布局情况只需多次调用addType进行添加即可

        //扩展的使用
        list.config(data) {
            //添加视图类型
            addType<Head>(R.layout.item_head) { d, h, p -> // d: 数据, h: viewholder, p: 下标
                //视图绑定数据
                h.root.img.setImageResource(d.img)
            }
            //添加视图类型
            addType<User1>(R.layout.item_user1) { d, h, p -> // d: 数据, h: viewholder, p: 下标
                //视图绑定数据
                h.root.name1.text = "Name: ${d.name}"
                h.root.age1.text = "Age: ${d.age}"
            }
            //添加视图类型，KT-Extensions支持
            addType<User2, KTViewHolder>(R.layout.item_user2) { d, h, p -> // d: 数据, h: viewholder, p: 下标
                //视图绑定数据
                h.name2.text = "Name: ${d.name}"
                h.age2.text = "Age: ${d.age}"
            }
        }

    }

}
~~~

### 如喜欢且很有帮助可以请作者吃冰棒哦~~~
![zfb.jpg](https://upload-images.jianshu.io/upload_images/4191132-ba6603f3825d069f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

