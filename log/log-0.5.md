### 增强默认ViewHolder的实现
- 新增Payloads函数，实现了局部刷新View
~~~ kotlin
addType<User1>(R.layout.item_user1, {d, h, p, payloads -> // d: 数据, h: viewholder, p: 下标, payloads: 局部信息
    //局部刷新
}) { d, h, p -> // d: 数据, h: viewholder, p: 下标
    //全量刷新
}
~~~