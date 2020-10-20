package cc.xiaobaicz.demo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import cc.xiaobaicz.recyclerview.extend.config
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_head.view.*
import kotlinx.android.synthetic.main.item_user1.view.age as age1
import kotlinx.android.synthetic.main.item_user1.view.name as name1
import kotlinx.android.synthetic.main.item_user2.view.age as age2
import kotlinx.android.synthetic.main.item_user2.view.name as name2

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

data class Head(var img: Int)

data class User1(var name: String, var age: Int)

data class User2(var name: String, var age: Int)