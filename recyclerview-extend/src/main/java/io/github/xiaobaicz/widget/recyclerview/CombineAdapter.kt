package io.github.xiaobaicz.widget.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * 组合适配器便捷方法
 * @param isolate 隔离ViewType
 */
fun RecyclerView.combineAdapter(isolate: Boolean = false, config: Combine.() -> Unit): CombineAdapter {
    val combine = Combine()
    combine.config()
    val adapter = CombineAdapter(combine, isolate)
    this.adapter = adapter
    return adapter
}

/**
 * 视图类型 & Binding 关联
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified V : ViewBinding, D : ViewType> Combine.bind(viewType: Int, binding: OnBinding<V, D>) {
    factoryMap[viewType] = BindingFactory.create<V>() as BindingFactory<ViewBinding>
    bindingMap[viewType] = binding as OnBinding<ViewBinding, ViewType>
}

/**
 * 视图类型
 */
interface ViewType {
    val viewType: Int
}

/**
 * Factory&Binding组合
 */
class Combine(
    val factoryMap: HashMap<Int, BindingFactory<ViewBinding>> = HashMap(),
    val bindingMap: HashMap<Int, OnBinding<ViewBinding, ViewType>> = HashMap(),
)

/**
 * 组合适配器，多类型列表
 * @param isolate 隔离ViewType
 */
class CombineAdapter(
    private val combine: Combine,
    private val isolate: Boolean = false,
) : BindingListAdapter<ViewBinding, ViewType>() {
    // Binding创建回调
    val onBindingCreateMap = HashMap<Class<*>, OnBindingCreate<ViewBinding>>()

    private val typeMap = HashMap<Int, Int>(2)

    override fun bindingFactory(viewType: Int): BindingFactory<ViewBinding> {
        val rawType = if (!isolate) viewType else typeMap[viewType]
        return combine.factoryMap[rawType] ?: throw NullPointerException("not find factory by viewType: $rawType")
    }

    override fun onCreate(bind: ViewBinding) {
        onBindingCreateMap[bind::class.java]?.onCreate(bind)
    }

    override fun onBind(bind: ViewBinding, position: Int) {
        val any = data[position]
        val binding = combine.bindingMap[any.viewType] ?: throw NullPointerException("not find binding by viewType: ${any.viewType}")
        binding.onBind(bind, any, position)
    }

    override fun getItemViewType(position: Int): Int {
        val any = data[position]
        if (!isolate) return any.viewType
        val type = System.identityHashCode(any)
        typeMap[type] = any.viewType
        return type
    }

    inline fun <reified V : ViewBinding> doOnBindingCreate(onBindingCreate: OnBindingCreate<V>): CombineAdapter {
        @Suppress("UNCHECKED_CAST")
        onBindingCreateMap[V::class.java] = onBindingCreate as OnBindingCreate<ViewBinding>
        return this
    }
}