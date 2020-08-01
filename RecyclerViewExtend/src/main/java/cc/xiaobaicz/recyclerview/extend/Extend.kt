package cc.xiaobaicz.recyclerview.extend

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 配置入口
 * @param data 数据源：任意类型，支持混合类型
 * @param lm 布局管理器：默认线性布局
 * @param config 类型建造者函数，通过 addType 添加 类型对应布局 和 视图绑定函数
 */
fun <D: Any> RecyclerView.config(data: MutableList<D>, lm: RecyclerView.LayoutManager = LinearLayoutManager(context), config: Builder.()->Unit) {
    //生成类型建造者
    val builder = Builder()
    //用户添加类型配置
    config(builder)
    //布局管理器配置
    layoutManager = lm
    //通用适配器配置
    adapter = AdapterX(context, data, builder.map)
}

/**
 * 类型建造者
 */
class Builder {
    /**
     * 类型，布局，视图绑定函数 的集合，以数据类型为 Key
     */
    val map = HashMap<Class<*>, ItemType<*>>()

    /**
     * 添加数据/视图类型函数
     * @param klass 数据类型class
     * @param resId 布局ID
     * @param func 视图绑定函数
     */
    fun <D: Any> addType(klass: Class<D>, resId: Int, func: (BindFunc<D>)?) {
        map[klass] = ItemType(resId, func)
    }
}