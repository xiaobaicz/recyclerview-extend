package io.github.xiaobaicz.widget.recyclerview

import androidx.viewbinding.ViewBinding

/**
 * 数据&视图绑定
 */
fun interface OnBinding<V, D> {
    /**
     * @param v 视图
     * @param d 数据
     * @param p 数据下标
     */
    fun onBind(v: V, d: D, p: Int)
}

/**
 * Binding创建回调
 */
fun interface OnBindingCreate<T : ViewBinding> {
    fun onCreate(bind: T)
}

/**
 * 尝试把ViewBinding转换为指定类型
 * @param T 目标类型
 */
inline fun <reified T : ViewBinding> ViewBinding.tryCast(block: T.() -> Unit) {
    if (this is T) block(this)
}
