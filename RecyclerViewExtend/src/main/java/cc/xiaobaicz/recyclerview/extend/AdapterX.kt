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
class AdapterX(context: Context, val content: Content) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        val holderClazz = content.holderTypes[viewType] ?: throw NullPointerException("view holder can not be empty")
        return holderClazz.getConstructor(View::class.java).newInstance(view)
    }

    /**
     * 通过布局ID产生唯一ItemType
     */
    override fun getItemViewType(position: Int): Int {
        val dataClass = content.getItemViewType(position).javaClass
        val type = content.types[dataClass] ?: throw NullPointerException("type can not be empty")
        return type.resId
    }

    /**
     * 数据项数
     */
    override fun getItemCount() = content.size

    /**
     * 数据绑定视图
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    /**
     * 数据绑定视图
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        val d = content.getItemViewType(position) //数据
        val dataClass = d.javaClass //数据类Class
        val type = content.types[dataClass] //数据类型对应绑定函数
        bind(d, holder, position, type?.bind, payloads) //绑定操作
    }

    /**
     * 绑定回调函数 Object 转 实际类型
     */
    @Suppress("UNCHECKED_CAST")
    private fun <D: Any, H: RecyclerView.ViewHolder> bind(d: D, viewHolder: H, position: Int, bindFunc: BindFunc<D, H>?, payloads: MutableList<Any>) {
        bindFunc?.bind(d, viewHolder, position, payloads)
    }


    /**
     * 更新元素
     * @since 0.6.0
     */
    fun notifyItemChangedX(position: Int, payload: Any? = null) {
        notifyItemChanged(position + content.headers.size, payload)
    }

    /**
     * 插入元素
     * @since 0.6.0
     */
    fun notifyItemInsertedX(position: Int) {
        notifyItemInserted(position + content.headers.size)
    }

    /**
     * 移动元素
     * @since 0.6.0
     */
    fun notifyItemMovedX(fromPosition: Int, toPosition: Int) {
        notifyItemMoved(fromPosition + content.headers.size, toPosition + content.headers.size)
    }

    /**
     * 范围更新元素
     * @since 0.6.0
     */
    fun notifyItemRangeChangedX(position: Int, itemCount: Int) {
        notifyItemRangeChanged(position + content.headers.size, itemCount)
    }

    /**
     * 范围插入元素
     * @since 0.6.0
     */
    fun notifyItemRangeInsertedX(position: Int, itemCount: Int) {
        notifyItemRangeInserted(position + content.headers.size, itemCount)
    }

    /**
     * 范围移除元素
     * @since 0.6.0
     */
    fun notifyItemRangeRemovedX(position: Int, itemCount: Int) {
        notifyItemRangeRemoved(position + content.headers.size, itemCount)
    }

    /**
     * 移除元素
     * @since 0.6.0
     */
    fun notifyItemRemovedX(position: Int) {
        notifyItemRemoved(position + content.headers.size)
    }

    /**
     * 更新头部元素
     * @since 0.6.0
     */
    fun notifyHeaderChangedX(position: Int) {
        notifyItemChanged(position)
    }

    /**
     * 更新脚部元素
     * @since 0.6.0
     */
    fun notifyFootChangedX(position: Int) {
        notifyItemChanged(content.headers.size + content.bodySize() + position)
    }

    /**
     * 移除头部元素
     * @since 0.6.0
     */
    fun notifyHeaderRemovedX(position: Int) {
        content.headers.remove(position)
        notifyItemRemoved(position)
    }

    /**
     * 移除脚部元素
     * @since 0.6.0
     */
    fun notifyFootRemovedX(position: Int) {
        content.foots.remove(position)
        notifyItemRemoved(content.headers.size + content.bodySize() + position)
    }

    /**
     * 列表拼接
     */
    fun concatContent(content: MutableList<out Any>) {
        val oldSize = this.content.size
        this.content.concatContent(content)
        notifyItemRangeInsertedX(oldSize, content.size)
    }

    /**
     * 移除主列表拼接
     */
    fun removeContent(content: MutableList<out Any>) {
        val oldSize = this.content.size
        this.content.removeContent(content)
        notifyItemRangeRemovedX(oldSize, content.size)
    }

}