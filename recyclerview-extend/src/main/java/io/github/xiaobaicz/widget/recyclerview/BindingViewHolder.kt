package io.github.xiaobaicz.widget.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Binding的[RecyclerView.ViewHolder]包装
 */
class BindingViewHolder<T : ViewBinding>(val bind: T) : RecyclerView.ViewHolder(bind.root)