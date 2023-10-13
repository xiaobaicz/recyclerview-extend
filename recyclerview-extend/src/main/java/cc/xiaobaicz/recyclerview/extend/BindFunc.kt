package cc.xiaobaicz.recyclerview.extend

import androidx.recyclerview.widget.RecyclerView

/**
 * 视图绑定函数
 * @param D 数据类型
 * @param H ViewHolder类型
 */
fun interface BindFunc<D: Any, H: RecyclerView.ViewHolder> {
    /**
     * 绑定转换
     * @param data 数据
     * @param holder view holder
     * @param position 下标
     */
    fun bind(data: D, holder: H, position: Int, payloads: MutableList<Any>)
}