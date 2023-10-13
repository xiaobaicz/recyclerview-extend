package cc.xiaobaicz.recyclerview.extend

import androidx.recyclerview.widget.RecyclerView

/**
 * Item类型数据集
 */
data class ItemType(
    /**
     * Item对应的布局ID
     */
    val resId: Int,
    /**
     * Item绑定数据的回调函数
     */
    val bind: BindFunc<Any, RecyclerView.ViewHolder>?
)