package io.github.xiaobaicz.widget.recyclerview

import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * BindingAdapter基础上集成 数据集 & DiffUtil
 */
abstract class BindingListAdapter<V: ViewBinding, D : Any> : BindingAdapter<V>() {

    private val callback: CallbackWrapper<D> = CallbackWrapper()

    private val mainScope = MainScope()

    // reset计数器
    private var count = 0

    /**
     * 数据集，通过重新赋值触发DiffUtil
     */
    var data: List<D> = emptyList()
        set(data) {
            mainScope.launch {
                val id = ++count
                // 异步计算差异
                val diff = calculateDiff(field, data)
                // 只获取最后一次计算结果
                if (id != count) return@launch
                field = data
                diff.dispatchUpdatesTo(this@BindingListAdapter)
            }
        }

    private suspend fun calculateDiff(old: List<D>, data: List<D>): DiffUtil.DiffResult {
        return withContext(Dispatchers.IO) {
            val newData = if (old == data) data.toList() else data
            callback.oldData = old
            callback.newData = newData
            DiffUtil.calculateDiff(callback)
        }
    }

    override fun getItemCount(): Int = data.size

    /**
     * DiffUtil对比器
     */
    fun setCallback(callback: CompareCallback<D>): BindingListAdapter<V, D> {
        this.callback.callback = callback
        return this
    }

    /**
     * DiffUtil对比器
     */
    interface CompareCallback<D> {
        /**
         * 对象是否一致
         * @return true 一致
         */
        fun areItemsTheSame(oldData: D, newData: D): Boolean

        /**
         * 对象一致的前提下，对象内容是否一致。判断是否触发Payload
         * @return true 内容一致
         */
        fun areContentsTheSame(oldData: D, newData: D): Boolean = true

        /**
         * 获取Payload对象
         * @return Payload对象
         */
        fun getChangePayload(oldData: D, newData: D): D? = null
    }

    // DiffUtil对比器的包装类
    private class CallbackWrapper<D> : DiffUtil.Callback() {
        var callback: CompareCallback<D>? = null
        var oldData: List<D> = emptyList()
        var newData: List<D> = emptyList()
        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return callback?.areItemsTheSame(oldData[oldItemPosition], newData[newItemPosition]) ?: false
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return callback?.areContentsTheSame(oldData[oldItemPosition], newData[newItemPosition]) ?: false
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return callback?.getChangePayload(oldData[oldItemPosition], newData[newItemPosition])
        }
    }

}