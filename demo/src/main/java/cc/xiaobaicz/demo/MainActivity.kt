package cc.xiaobaicz.demo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.demo.entity.Foot1
import cc.xiaobaicz.demo.entity.Foot2
import cc.xiaobaicz.demo.entity.Header1
import cc.xiaobaicz.demo.entity.Header2
import cc.xiaobaicz.recyclerview.extend.config
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user2.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //创建数据集
        val data = ArrayList<Any>()
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
            //添加头部
            addHeader<Header1>(Header1(), R.layout.item_header1)
            addHeader<Header2>(Header2(), R.layout.item_header2)
            //添加尾部
            addFoot<Foot1>(Foot1(), R.layout.item_foot1)
            addFoot<Foot2>(Foot2(), R.layout.item_foot2)
        }

    }

}

data class Head(var img: Int)

data class User1(var name: String, var age: Int)

data class User2(var name: String, var age: Int)