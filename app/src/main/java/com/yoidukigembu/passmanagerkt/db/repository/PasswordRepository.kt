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

}