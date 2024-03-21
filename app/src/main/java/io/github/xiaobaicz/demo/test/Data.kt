package io.github.xiaobaicz.demo.test

import io.github.xiaobaicz.demo.bean.Img
import io.github.xiaobaicz.demo.bean.Msg
import io.github.xiaobaicz.widget.recyclerview.ViewType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

suspend fun newsData() = withContext(Dispatchers.IO) {
    delay(2000L)
    ArrayList<ViewType>().apply {
        val cs = arrayOf(0xffff0000, 0xff00ff00, 0xff0000ff)
        repeat(1000) {
            if (it and 1 == 0) {
                add(Msg("震惊${it}号", "震惊震惊震惊震惊震惊震惊震惊震"))
            } else {
                add(Img("震惊${it}号", cs[it % cs.size]))
            }
        }
    }
}