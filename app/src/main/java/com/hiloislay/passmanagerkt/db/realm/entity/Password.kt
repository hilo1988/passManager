package com.hiloislay.passmanagerkt.db.realm.entity

import com.hiloislay.passmanagerkt.accessor.PasswordDataAccessor
import com.hiloislay.passmanagerkt.valueobject.Cryptor
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Password() : RealmObject() {

    constructor(accessor: PasswordDataAccessor) : this() {
        if (accessor.getPrimaryId() != null) {
            this.id = accessor.getPrimaryId()!!
        }
        this.loginId = accessor.getLoginId()
        this.name = accessor.getLabelName()

        val cryptor = Cryptor.getInstance()
        this.password1 = cryptor.encrypt(accessor.getPassword1()!!)
        this.password2 = accessor.getPassword2()?.let { cryptor.encrypt(it) }
        this.loginUrl = accessor.getLoginUrl()
        this.memo = accessor.getMemo()
    }

    @PrimaryKey
    var id: Long = 0

    /** ログインID */
    var loginId: String? = null


    /** 名前 */
    var name: String? = null

    /** 第一パスワード */
    var password1: ByteArray? = null

    /** 第二パスワード */
    var password2: ByteArray? = null


    /** ログインURL */
    var loginUrl: String? = null

    /** メモ */
    var memo: String? = null

    /** ORDER BY キー */
    var orderByKey: Int? = null

}