package cc.xiaobaicz.demo_viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.demo_viewbinding.databinding.ActivityMainBinding
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
                    icon.setImageResource(d.icon)
                    name.text = d.name
                    phone.text = d.phone
                }
            }
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