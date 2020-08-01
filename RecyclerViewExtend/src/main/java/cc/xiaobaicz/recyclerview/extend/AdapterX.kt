package cc.xiaobaicz.recyclerview.extend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

/**
 * 通用适配器
 * @param D 数据类型泛型
 * @param context
 * @param data 数据集
 * @param types 数据类型处理方案
 */
class AdapterX<D: Any>(context: Context, private val data: MutableList<D>, private val types: Map<Class<*>, ItemType<*>>) : RecyclerView.Adapter<ViewHolderX>() {

    /**
     * context引用
     */
    private val ref = WeakReference(context)

    /**
     * 创建ViewHolderX
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderX {
        val context = ref.get() ?: throw NullPointerException("context can not be empty")
        val view = LayoutInflater.from(context).inflate(viewType, parent, false)
        return ViewHolderX(view)
    }

    /**
     * 通过布局ID产生唯一ItemType
     */
    override fun getItemViewType(position: Int): Int {
        val dataClass = data[position].javaClass
        val type = types[dataClass] ?: throw NullPointerException("type can not be empty")
        return type.resId
    }

    /**
     * 数据项数
     */
    override fun getItemCount() = data.size

    /**
     * 数据绑定视图
     */
    override fun onBindViewHolder(holder: ViewHolderX, position: Int) {
        val d = data[position] //数据
        val dataClass = d.javaClass //数据类Class
        val type = types[dataClass] //数据类型对应绑定函数
        bind(d, holder, position, type?.bind) //绑定操作
    }

    /**
     * 绑定回调函数 Object 转 实际类型
     */
    @Suppress("UNCHECKED_CAST")
    private fun <D: Any> bind(d: Any, viewHolder: ViewHolderX, position: Int, bindFunc: BindFunc<D>?) {
        bindFunc?.invoke(d as D, viewHolder, position)
    }

}