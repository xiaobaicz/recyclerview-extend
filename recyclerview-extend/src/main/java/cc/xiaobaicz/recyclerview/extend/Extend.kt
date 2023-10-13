package cc.xiaobaicz.recyclerview.extend

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 配置入口
 * @param data 数据源：任意类型，支持混合类型
 * @param lm 布局管理器：默认线性布局
 * @param config 类型建造者函数，通过 addType 添加 类型对应布局 和 视图绑定函数
 */
fun RecyclerView.config(data: MutableList<out Any>, lm: RecyclerView.LayoutManager = LinearLayoutManager(context), config: Builder.()->Unit): Content {
    val content = Content(data)
    //生成类型建造者
    val builder = Builder(content)
    //用户添加类型配置
    config(builder)
    //布局管理器配置
    layoutManager = lm
    //通用适配器配置
    adapter = AdapterX(context, content)
    return content
}

/**
 * 类型建造者
 */
class Builder(
    /**
     * 列表内容
     */
    val content: Content
) {

    /**
     * 添加数据/视图类型函数
     * @param resId 布局ID
     * @param func 视图绑定函数
     * @since 0.2
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified D: Any, reified H: RecyclerView.ViewHolder> addType(resId: Int, func: BindFunc<D, H>?) {
        content.types[D::class.java as Class<Any>] = ItemType(resId, func as BindFunc<Any, RecyclerView.ViewHolder>?)
        content.holderTypes[resId] = H::class.java as Class<RecyclerView.ViewHolder>
    }

    /**
     * 添加数据/视图类型函数
     * @param resId 布局ID
     * @param func 视图绑定函数
     * @since 0.3
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified D: Any> addType(resId: Int, func: BindFunc<D, ViewHolderX>?): Int {
        content.types[D::class.java as Class<Any>] = ItemType(resId, func as BindFunc<Any, RecyclerView.ViewHolder>?)
        content.holderTypes[resId] = ViewHolderX::class.java as Class<RecyclerView.ViewHolder>
        return 0
    }

    /**
     * 添加头部类型函数
     * 如需移除拼接，调用
     * @see AdapterX.notifyHeaderRemovedX
     * 如需更新，调用
     * @see AdapterX.notifyHeaderChangedX
     * @param resId 布局ID
     * @param func 视图绑定函数
     * @since 0.6.0
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified D: Any, reified H: RecyclerView.ViewHolder> addHeader(header: D, resId: Int, func: BindFunc<D, H>? = null) {
        content.headers.add(header)
        content.types[D::class.java as Class<Any>] = ItemType(resId, func as BindFunc<Any, RecyclerView.ViewHolder>?)
        content.holderTypes[resId] = H::class.java as Class<RecyclerView.ViewHolder>
    }

    /**
     * 添加头部类型函数
     * 如需移除拼接，调用
     * @see AdapterX.notifyHeaderRemovedX
     * 如需更新，调用
     * @see AdapterX.notifyHeaderChangedX
     * @param resId 布局ID
     * @param func 视图绑定函数
     * @since 0.6.0
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified D: Any> addHeader(header: D, resId: Int, func: BindFunc<D, ViewHolderX>? = null): Int {
        content.headers.add(header)
        content.types[D::class.java as Class<Any>] = ItemType(resId, func as BindFunc<Any, RecyclerView.ViewHolder>?)
        content.holderTypes[resId] = ViewHolderX::class.java as Class<RecyclerView.ViewHolder>
        return 0
    }

    /**
     * 添加尾部类型函数
     * 如需移除拼接，调用
     * @see AdapterX.notifyFootRemovedX
     * 如需更新，调用
     * @see AdapterX.notifyFootChangedX
     * @param resId 布局ID
     * @param func 视图绑定函数
     * @since 0.6.0
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified D: Any, reified H: RecyclerView.ViewHolder> addFoot(foot: D, resId: Int, func: BindFunc<D, H>? = null) {
        content.foots.add(foot)
        content.types[D::class.java as Class<Any>] = ItemType(resId, func as BindFunc<Any, RecyclerView.ViewHolder>?)
        content.holderTypes[resId] = H::class.java as Class<RecyclerView.ViewHolder>
    }

    /**
     * 添加尾部类型函数
     * 如需移除拼接，调用
     * @see AdapterX.notifyFootRemovedX
     * 如需更新，调用
     * @see AdapterX.notifyFootChangedX
     * @param resId 布局ID
     * @param func 视图绑定函数
     * @since 0.6.0
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified D: Any> addFoot(foot: D, resId: Int, func: BindFunc<D, ViewHolderX>? = null): Int {
        content.foots.add(foot)
        content.types[D::class.java as Class<Any>] = ItemType(resId, func as BindFunc<Any, RecyclerView.ViewHolder>?)
        content.holderTypes[resId] = ViewHolderX::class.java as Class<RecyclerView.ViewHolder>
        return 0
    }

    /**
     * 拼接主内容列表，如需移除拼接，调用
     * @see AdapterX.removeContent
     */
    fun concatContent(content: MutableList<Any>) {
        this.content.concatContent(content)
    }

}

/**
 * 获取AdapterX
 * @since 0.6.0
 */
val RecyclerView.adapterX: AdapterX get() {
    val adapter = adapter
    if (adapter is AdapterX) {
        return adapter
    }
    throw ClassCastException("${adapter?.javaClass} is not ${AdapterX::javaClass}")
}
