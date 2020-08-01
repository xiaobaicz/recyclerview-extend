package cc.xiaobaicz.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.recyclerview.extend.config
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_head.view.*
import kotlinx.android.synthetic.main.item_user1.view.name as name1
import kotlinx.android.synthetic.main.item_user1.view.age as age1
import kotlinx.android.synthetic.main.item_user2.view.name as name2
import kotlinx.android.synthetic.main.item_user2.view.age as age2

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

        //扩展的使用
        list.config(data) {
            //添加视图类型
            addType(Head::class.java, R.layout.item_head) { d, h, p -> // d: 数据, h: viewholder, p: 下标
                //视图绑定数据
                h.root.img.setImageResource(d.img)
            }
            //添加视图类型
            addType(User1::class.java, R.layout.item_user1) { d, h, p -> // d: 数据, h: viewholder, p: 下标
                //视图绑定数据
                h.root.name1.text = "Name: ${d.name}"
                h.root.age1.text = "Age: ${d.age}"
            }
            //添加视图类型
            addType(User2::class.java, R.layout.item_user2) { d, h, p -> // d: 数据, h: viewholder, p: 下标
                //视图绑定数据
                h.root.name2.text = "Name: ${d.name}"
                h.root.age2.text = "Age: ${d.age}"
            }
        }

    }

}

data class Head(var img: Int)

data class User1(var name: String, var age: Int)

data class User2(var name: String, var age: Int)