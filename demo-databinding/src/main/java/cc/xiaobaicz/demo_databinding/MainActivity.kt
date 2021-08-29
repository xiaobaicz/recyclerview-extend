package cc.xiaobaicz.demo_databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.demo_databinding.databinding.ActivityMainBinding
import cc.xiaobaicz.demo_databinding.entity.*
import cc.xiaobaicz.recyclerview.extend.config

class MainActivity: AppCompatActivity() {

    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val data = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        //RecyclerView配置扩展
        bind.list.config(data) {
            addType<Account, Account.ViewHolder>(R.layout.item_account) { d, h, p, payloads ->
                //数据绑定
                with(h.bind) {
                    setVariable(BR.account, d)
                    executePendingBindings()
                }
            }
            //添加头部
            addHeader<Header1>(Header1(), R.layout.item_header1)
            addHeader<Header2>(Header2(), R.layout.item_header2)
            //添加尾部
            addFoot<Foot1>(Foot1(), R.layout.item_foot1)
            addFoot<Foot2>(Foot2(), R.layout.item_foot2)
        }

        //测试数据
        testData()
    }

    private fun testData() {
        for (i in 0 until 100) {
            data.add(Account(R.mipmap.ic_launcher, "Account$i", "12345678"))
        }
        bind.list.adapter?.notifyDataSetChanged()
    }

}