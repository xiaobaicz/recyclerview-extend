### 增强默认ViewHolder的实现
- 新增findViewById函数，该实现了缓存View
~~~ kotlin
addType<User1>(R.layout.item_user1) { d, h, p -> // d: 数据, h: viewholder, p: 下标
//视图绑定数据
    h.findViewById<TextView>(R.id.name).text = "Name: ${d.name}"
    h.findViewById<TextView>(R.id.age).text = "Age: ${d.age}"
}
~~~