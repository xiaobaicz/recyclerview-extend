package cc.xiaobaicz.recyclerview.extend

import androidx.recyclerview.widget.RecyclerView

/**
 * 列表内容
 */
class Content(
    /**
     * 内容类型
     */
    val contents: MutableList<Any>,
    /**
     * 类型，布局，视图绑定函数 的集合，以数据类型为 Key
     */
    val types: MutableMap<Class<Any>, ItemType> = HashMap(),
    /**
     * 类型，布局，视图绑定函数 的集合，以数据类型为 Key
     */
    val holderTypes: MutableMap<Int, Class<RecyclerView.ViewHolder>> = HashMap(),
    /**
     * 头部类型
     */
    val headers: MutableList<Any> = ArrayList(),
    /**
     * 尾部类型
     */
    val foots: MutableList<Any> = ArrayList()
) {
    /**
     * 获取元素类型
     */
    fun getItemViewType(position: Int): Any {
        return when {
            //头部
            position < headers.size -> headers[position]
            //主体
            position - headers.size < contents.size -> contents[position - headers.size]
            //尾部
            else -> foots[position - headers.size - contents.size]
        }
    }

    /**
     * 列表总元素个数 (包含 头尾)
     */
    val size: Int get() = headers.size + contents.size + foots.size
}