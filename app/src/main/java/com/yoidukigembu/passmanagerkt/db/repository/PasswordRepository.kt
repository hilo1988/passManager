package com.yoidukigembu.passmanagerkt.db.repository

import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.db.entity.Password_Relation

interface PasswordRepository {

    fun findById(id: Long): Password?

    /**
     * パスワードリスト用にカーソルを取得
     */
    fun getListCursor(): Password_Relation

    /**
     * IDをキーに削除
     */
    fun deleteById(id: Long): Int

    /**
     * パスワードのインサート
     */
    fun insert(entity: Password): Long

    /**
     * パスワード更新
     */
    fun update(entity: Password): Int


    /**
     * 並び順の更新
     */
    fun updateOrderByKey(id: Long, orderByKey: Int): Int


    /**
     * 並び順の最大値をセレクト
     */
    fun selectMaxOrderByKey(): Int
}