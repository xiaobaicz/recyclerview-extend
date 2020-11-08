package cc.xiaobaicz.recyclerview.extend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

/**
 * 通用适配器
 * @param context
 * @param data 数据集
 * @param types 数据类型处理方案
 */
class AdapterX(
    context: Context, private val data: MutableList<Any>,
    private val types: Map<Class<Any>, ItemType>,
    private val holderTypes: Map<Int, Class<RecyclerView.ViewHolder>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * context引用
     */
    private val ref = WeakReference(context)

    /**
     * 创建ViewHolderX
     * @param viewType
     */
    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = ref.get() ?: throw NullPointerException("context can not be empty")
        val view = LayoutInflater.from(context).inflate(viewType, parent, false)
        val holderClazz = holderTypes[viewType] ?: throw NullPointerException("view holder can not be empty")
        return holderClazz.getConstructor(View::class.java).newInstance(view)
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
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val d = data[position] //数据
        val dataClass = d.javaClass //数据类Class
        val type = types[dataClass] //数据类型对应绑定函数
        bind(d, holder, position, type?.bind) //绑定操作
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        val d = data[position] //数据
        val dataClass = d.javaClass //数据类Class
        val type = types[dataClass] //数据类型对应绑定函数
        if (payloads.isNullOrEmpty() || type?.bindPayloads == null) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            bind(d, holder, position, type.bindPayloads, payloads) //绑定操作
        }
    }

    /**
     * 绑定回调函数 Object 转 实际类型
     */
    @Suppress("UNCHECKED_CAST")
    private fun <D: Any, H: RecyclerView.ViewHolder> bind(d: D, viewHolder: H, position: Int, bindFunc: BindFunc<D, H>?) {
        bindFunc?.bind(d, viewHolder, position)
    }

    /**
     * 绑定回调函数 Object 转 实际类型
     */
    @Suppress("UNCHECKED_CAST")
    private fun <D: Any, H: RecyclerView.ViewHolder> bind(d: D, viewHolder: H, position: Int, bindFunc: BindFuncPayloads<D, H>?, payloads: MutableList<Any>) {
        bindFunc?.bind(d, viewHolder, position, payloads)
    }

}