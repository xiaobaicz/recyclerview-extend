package io.github.xiaobaicz.demo.bean

import io.github.xiaobaicz.demo.constant.VT_MSG
import io.github.xiaobaicz.widget.recyclerview.ViewType

class Msg(
    val title: String,
    val msg: String,
) : ViewType {
    override val viewType: Int get() = VT_MSG
}
