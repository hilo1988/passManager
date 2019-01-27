package com.hiloislay.passmanagerkt.valueobject

import com.hiloislay.passmanagerkt.accessor.MenuDataAccessor

/**
 * メニュー表示用データクラス
 */
data class MenuData(val id: Long, val name: String) {

    constructor(accessor: MenuDataAccessor) : this(accessor.getMenuId(), accessor.getMenuName())

}