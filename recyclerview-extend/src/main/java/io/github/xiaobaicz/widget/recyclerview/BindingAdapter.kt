package io.github.xiaobaicz.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * ViewBinding支持的RecyclerView.Adapter
 */
abstract class BindingAdapter<V : ViewBinding> : RecyclerView.Adapter<BindingViewHolder<V>>(),
    OnBindingCreate<V> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<V> {
        // 转换为Binding创建
        val bind = bindingFactory(viewType).create(LayoutInflater.from(parent.context), parent, false)
        onCreate(bind)
        return BindingViewHolder(bind)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<V>, position: Int) {
        // 转换为Binding回调
        onBind(holder.bind, position)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<V>, position: Int, payloads: MutableList<Any>) {
        // 转换为Binding回调，payloads模式
        onBind(holder.bind, position, payloads)
    }

    /**
     * Binding工厂，通过 [BindingFactory.Companion.create] 提供
     */
    abstract fun bindingFactory(viewType: Int): BindingFactory<V>

    /**
     * Binding创建回调
     */
    override fun onCreate(bind: V) {}

    /**
     * 绑定数据
     */
    abstract fun onBind(bind: V, position: Int)

    /**
     * 绑定数据，payloads模式
     */
    open fun onBind(bind: V, position: Int, payloads: MutableList<Any>) {
        onBind(bind, position)
    }

}