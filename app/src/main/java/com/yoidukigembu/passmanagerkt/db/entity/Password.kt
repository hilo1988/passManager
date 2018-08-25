package com.yoidukigembu.passmanagerkt.db.entity

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Table

/**
 * パスワード用エンティティ
 *
 * @author BALIUS
 */
@Table("passwords")
class Password : BaseEntity() {


    @Column("_id")
    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    /**
     * ログインID
     */
    @Column("login_id")
    var loginId: String? = null

    /**
     * 名前
     */
    @Column("name")
    var name: String? = null

    /**
     * 第一パスワード
     */
    @Column("password1")
    var password1: ByteArray? = null

    /**
     * 第二パスワード
     */
    @Column("password2")
    var password2: ByteArray? = null

    /**
     * ログインURL
     */
    @Column("login_url")
    var loginUrl: String? = null

    /**
     * メモ
     */
    @Column("memo")
    var memo: String? = null

    /**
     * ORDER BY キー
     */
    @Column(value = "order_by_key", indexed = true)
    var orderByKey: Int? = null



}
