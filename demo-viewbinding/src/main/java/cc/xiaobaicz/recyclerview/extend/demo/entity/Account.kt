package cc.xiaobaicz.recyclerview.extend.demo.entity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cc.xiaobaicz.recyclerview.extend.demo.databinding.ItemAccountBinding

/**
 * 数据类
 */
data class Account(val icon: Int, val name: String, val phone: String) {

    /**
     * 自定义支持ViewBinding的ViewHolder
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bind by lazy {
            ItemAccountBinding.bind(view)
        }
    }

}