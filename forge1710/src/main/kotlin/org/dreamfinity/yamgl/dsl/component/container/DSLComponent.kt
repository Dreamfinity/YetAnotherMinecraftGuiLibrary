package org.dreamfinity.yamgl.dsl.component.container

import org.dreamfinity.yamgl.dsl.context.container.ComponentContext

interface DSLComponent<T: Any, C: ComponentContext<T>> {
    operator fun invoke(init: C.() -> Unit): T
}