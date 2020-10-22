package cc.xiaobaicz.demo_viewmodel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cc.xiaobaicz.demo_viewmodel.databinding.ItemAccountBinding

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