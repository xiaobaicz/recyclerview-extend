package cc.xiaobaicz.recyclerview.extend

import androidx.recyclerview.widget.RecyclerView

/**
 * 列表内容
 */
class Content(
    /**
     * 内容类型
     */
    contents: MutableList<out Any>,
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
     * 主内容列表，由多个子列表构成，可动态拼接
     * @see Content.concatContent
     */
    private val body: MutableList<MutableList<out Any>> = arrayListOf(contents)

    /**
     * 获取元素类型
     */
    fun getItemViewType(position: Int): Any {
        return when {
            //头部
            position < headers.size -> headers[position]
            //主体
            position - headers.size < bodySize() -> getBodyItemViewType(position - headers.size)
            //尾部
            else -> foots[position - headers.size - bodySize()]
        }
    }

    /**
     * 拼接主内容列表
     */
    fun concatContent(content: MutableList<out Any>) {
        body.add(content)
    }

    /**
     * 移除主内容列表拼接
     */
    fun removeContent(content: MutableList<out Any>) {
        body.remove(content)
    }

    /**
     * 获取主内容列表元素的视图类型
     */
    private fun getBodyItemViewType(position: Int): Any {
        var p = position
        for (i in body) {
            if (p < i.size) {
                return i[p]
            }
            p -= i.size
        }
        throw ArrayIndexOutOfBoundsException("position > body size")
    }

    /**
     * 获取主内容列表大小
     */
    fun bodySize(): Int {
        var size = 0
        for (i in body) {
            size += i.size
        }
        return size
    }

    /**
     * 列表总元素个数 (包含 头尾)
     */
    val size: Int get() = headers.size + bodySize() + foots.size
}