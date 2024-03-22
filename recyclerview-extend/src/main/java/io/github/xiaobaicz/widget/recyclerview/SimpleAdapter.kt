package io.github.xiaobaicz.widget.recyclerview

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * 简单适配器便捷方法
 * @param isolate 隔离ViewType
 */
inline fun <reified V : ViewBinding, D : Any> RecyclerView.simpleAdapter(owner: LifecycleOwner, isolate: Boolean = false, onBinding: OnBinding<V, D>): SimpleAdapter<V, D> {
    val adapter = SimpleAdapter(owner, BindingFactory.create<V>(), onBinding, isolate)
    this.adapter = adapter
    return adapter
}

/**
 * 简单适配器，单一类型列表
 * @param isolate 隔离ViewType
 */
class SimpleAdapter<V : ViewBinding, D : Any>(
    owner: LifecycleOwner,
    private val factory: BindingFactory<V>,
    private val onBinding: OnBinding<V, D>,
    private val isolate: Boolean = false,
) : BindingListAdapter<V, D>(owner) {
    // Binding创建回调
    private var onBindingCreate: OnBindingCreate<V>? = null

    override fun bindingFactory(viewType: Int): BindingFactory<V> {
        return factory
    }

    override fun onCreate(bind: V) {
        onBindingCreate?.onCreate(bind)
    }

    override fun onBind(bind: V, position: Int) {
        onBinding.onBind(bind, data[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        if (!isolate) return 0
        return System.identityHashCode(data[position])
    }

    /**
     * Binding创建回调
     */
    fun doOnBindingCreate(onBindingCreate: OnBindingCreate<V>): SimpleAdapter<V, D> {
        this.onBindingCreate = onBindingCreate
        return this
    }
}