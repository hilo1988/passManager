package com.yoidukigembu.passmanagerkt.db.repository

import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.db.entity.Password_Relation
import io.reactivex.Observable
import io.reactivex.Single

interface PasswordRepository {

    fun findById(id: Long): Observable<Password>

    /**
     * パスワードリスト用にカーソルを取得
     */
    fun getListCursor(): Password_Relation

    /**
     * IDをキーに削除
     */
    fun deleteById(id: Long): Single<Int>

    /**
     * パスワードのインサート
     */
    fun insert(entity: Password): Single<Long>

    /**
     * パスワード更新
     */
    fun update(entity: Password): Single<Int>


    /**
     * 並び順の更新
     */
    fun updateOrderByKey(id: Long, orderByKey: Int): Single<Int>


    /**
     * 並び順の最大値をセレクト
     */
    fun selectMaxOrderByKey(): Single<Int>
}