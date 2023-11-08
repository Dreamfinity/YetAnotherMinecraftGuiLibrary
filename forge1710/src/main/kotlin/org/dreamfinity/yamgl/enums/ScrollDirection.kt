package org.dreamfinity.yamgl.enums

enum class ScrollDirection(scrollConst: Int) {
    UP(1),
    STALE(0),
    DOWN(-1);

    @JvmField
    val scrollConst: Int

    init {
        this.scrollConst = scrollConst.compareTo(0)
    }

    companion object {
        @JvmStatic
        fun fromDWheel(dWheel: Int): ScrollDirection {
            return if (dWheel > 0) DOWN else if (dWheel == 0) STALE else UP
        }
    }
}