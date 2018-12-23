package com.yoidukigembu.passmanagerkt.db.realm.repository


import com.yoidukigembu.passmanagerkt.db.realm.entity.Password
import io.reactivex.Single
import io.realm.RealmResults

interface PasswordRepository : BaseRepository<Password> {

    /**
     * パスワードリスト用にカーソルを取得
     */
//    fun getListCursor(): Password_Relation

    fun selectList(): RealmResults<Password>


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
    fun selectMaxOrderByKey(): Int
}