package cc.xiaobaicz.recyclerview.extend

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * 通用ViewHolder
 * @param root Item根视图
 */
class ViewHolderX(val root: View) : RecyclerView.ViewHolder(root) {

    /**
     * View缓存
     */
    private val viewMap = HashMap<Int, Reference<View?>>()

    /**
     * 通过View ID获取视图并缓存
     * @param resId View ID
     * @since 0.4
     */
    fun <T: View> findViewById(resId: Int): T {
        var v = viewMap[resId]  //获取View实例
        if (v?.get() == null) { //判空
            synchronized(ViewHolderX::class.java) {
                //查找操作
                v = viewMap[resId]
                //二次判空
                if (v?.get() == null) {
                    //通过弱引用包装结果
                    viewMap[resId] = WeakReference(root.findViewById(resId))
                    v = viewMap[resId]
                }
            }
        }
        assert(v?.get() != null)    //View判空
        return v?.get() as T
    }

}