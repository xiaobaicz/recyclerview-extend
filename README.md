### RecyclerView扩展
#### Adapter抽象
- BindingAdapter (实现ViewBinding抽象)
- BindingListAdapter (在ViewBinding抽象基础上实现DiffUtil，刷新数据)
#### Adapter简易实现
- SimpleAdapter
~~~ kotlin
val rv: RecyclerView
val adapter = rv.simpleAdapter<ViewBinding, Bean> { v, i, p ->
    // v: ViewBinding
    // i: Bean
    // p: Position
}
// 重新赋值刷新数据，DiffUtil自动刷新
adapter.data = listOf(x, x, x)
~~~

- CombineAdapter
~~~ kotlin
val rv: RecyclerView
val adapter = rv.combineAdapter {
    bind<ViewBinding1, Bean1>(viewType1) { v, i, p ->
        // v: ViewBinding1
        // i: Bean1
        // p: Position
    }
    bind<ViewBinding2, Bean2>(viewType2) { v, i, p ->
        // v: ViewBinding2
        // i: Bean2
        // p: Position
    }
}
// 重新赋值刷新数据，DiffUtil自动刷新
adapter.data = listOf(x, x, x)
~~~