package io.github.xiaobaicz.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method

/**
 * Binding工厂，通过反射进行创建Binding实例
 * @param method Binding生成方法
 */
class BindingFactory<V : ViewBinding>(private val method: Method) {

    /**
     * 创建Binding
     */
    fun create(inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean): V {
        @Suppress("UNCHECKED_CAST")
        return method.invoke(null, inflater, parent, attachToParent) as V
    }

    companion object {

        /**
         * 创建指定类型的BindingFactory
         * @param V 指定Binding类型
         */
        @JvmStatic
        inline fun <reified V : ViewBinding> create(): BindingFactory<V> {
            val clazz = V::class.java
            val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            return BindingFactory(method)
        }

    }

}
