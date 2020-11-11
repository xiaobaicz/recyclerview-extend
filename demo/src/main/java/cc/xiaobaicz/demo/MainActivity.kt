package cc.xiaobaicz.demo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.recyclerview.extend.config
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user2.view.*

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
            addType<Head>(R.layout.item_head) { d, h, p, payloads -> // d: 数据, h: viewholder, p: 下标, payloads: 局部刷新负载
                //视图绑定数据
                //通过默认ViewHolder方式  PS：（该方式缓存了View实例）
                h.findViewById<ImageView>(R.id.img).setImageResource(d.img)
            }
            //添加视图类型
            //通过默认ViewHolder方式  PS：（该方式缓存了View实例，性能较好）
            addType<User1>(R.layout.item_user1) { d, h, p, payloads -> // d: 数据, h: viewholder, p: 下标
                //视图绑定数据
                h.findViewById<TextView>(R.id.name).text = "Name: ${d.name}"
                h.findViewById<TextView>(R.id.age).text = "Age: ${d.age}"
                h.findViewById<TextView>(R.id.age).setOnClickListener {
                    list.adapter?.notifyItemChanged(p, Any())
                }
            }
            //添加视图类型，KT-Extensions支持
            addType<User2>(R.layout.item_user2) { d, h, p, payloads -> // d: 数据, h: viewholder, p: 下标
                //视图绑定数据
                //通过KT-Extension方式  PS：（该场景下没有缓存View，每次都直接findViewByID，性能最差）
                h.root.name.text = "Name: ${d.name}"
                h.root.age.text = "Age: ${d.age}"
            }
        }

    }

}

data class Head(var img: Int)

data class User1(var name: String, var age: Int)

data class User2(var name: String, var age: Int)