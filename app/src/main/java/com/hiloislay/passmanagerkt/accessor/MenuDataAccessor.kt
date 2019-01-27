package com.hiloislay.passmanagerkt.accessor

/**
 * メニューデータアクセサ
 */
interface MenuDataAccessor {

    /**
     * ID取得
     */
    fun getMenuId(): Long

    /**
     * メニュー名の取得
     */
    fun getMenuName(): String
}