package cc.xiaobaicz.recyclerview.extend

/**
 * Item类型数据集
 */
data class ItemType<D: Any>(
    /**
     * Item对应的布局ID
     */
    val resId: Int,
    /**
     * Item绑定数据的回调函数
     */
    val bind: (BindFunc<D>)?
)