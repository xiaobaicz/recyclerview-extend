### addType
> fun <D: Any> addType(klass: Class<D>, resId: Int, func: (BindFunc<D>)?)
>
> 该函数过期，提供更方便的addType函数，通过泛型确定Item类型，无需再传递class对象
>
> fun <reified D: Any> addType(resId: Int, noinline func: (BindFunc<D>)?)