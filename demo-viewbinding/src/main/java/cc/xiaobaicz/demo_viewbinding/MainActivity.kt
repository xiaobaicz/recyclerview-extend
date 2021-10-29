package cc.xiaobaicz.demo_viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.demo_viewbinding.databinding.ActivityMainBinding
import cc.xiaobaicz.demo_viewbinding.entity.*
import cc.xiaobaicz.recyclerview.extend.adapterX
import cc.xiaobaicz.recyclerview.extend.config

class MainActivity: AppCompatActivity() {

    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val data = ArrayList<Any>()
    private val data2 = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        //RecyclerView配置扩展
        bind.list.config(data) {
            addType<Account, Account.ViewHolder>(R.layout.item_account) { d, h, p, payloads ->
                //数据绑定
                with(h.bind) {
                    icon.setImageResource(d.icon)
                    name.text = d.name
                    phone.text = d.phone
                }
            }
            //添加头部
            addHeader<Header1>(Header1(), R.layout.item_header1)
            addHeader<Header2>(Header2(), R.layout.item_header2)
            //添加尾部
            addFoot<Foot1>(Foot1(), R.layout.item_foot1)
            addFoot<Foot2>(Foot2(), R.layout.item_foot2)
            //拼接列表，第一种方式
            concatContent(data2)
        }

//        //拼接列表，第二种方式
//        bind.list.adapterX.concatContent(data2)

//        bind.list.adapterX.apply {
//            //更新头部
//            notifyHeaderChangedX(0)
//            //删除头部
//            notifyHeaderRemovedX(0)
//            //更新尾部
//            notifyFootChangedX(0)
//            //删除尾部
//            notifyFootRemovedX(0)
//        }

        //测试数据
        testData(data)
        testData(data2)
    }

    private fun testData(data: MutableList<Any>) {
        for (i in 0 until 20) {
            data.add(Account(R.mipmap.ic_launcher, "Account$i", "12345678"))
        }
        bind.list.adapterX.notifyDataSetChanged()
    }

}