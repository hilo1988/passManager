package com.yoidukigembu.passmanagerkt.accessor

interface PasswordDataAccessor {

    fun getPrimaryId(): Long?

    /**
     * 項目名
     */
    fun getLabelName(): String?

    /**
     * ログインID
     */
    fun getLoginId(): String?

    /**
     * パスワード1
     */
    fun getPassword1(): String?

    /**
     * パスワード2
     */
    fun getPassword2(): String?

    /**
     * ログインURL
     */
    fun getLoginUrl(): String?

    /**
     * メモ
     */
    fun getMemo(): String?

}