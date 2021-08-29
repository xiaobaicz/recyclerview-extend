package cc.xiaobaicz.demo_databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import cc.xiaobaicz.demo_databinding.databinding.ItemAccountBinding

/**
 * 数据类
 */
data class Account(val icon: Int, val name: String, val phone: String) {

    companion object {
        @BindingAdapter("img")
        @JvmStatic
        fun bindImg(view: ImageView, id: Int) {
            view.setImageResource(id)
        }
    }

    /**
     * 自定义支持DataBinding的ViewHolder
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bind by lazy {
            ItemAccountBinding.bind(view)
        }
    }

}