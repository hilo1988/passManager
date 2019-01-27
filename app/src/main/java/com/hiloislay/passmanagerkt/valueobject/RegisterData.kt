package com.hiloislay.passmanagerkt.valueobject

/**
 * 登録データ
 */
data class RegisterData(
        val id: Long?,
        val loginId: String?,
        val name: String?,
        val password1: String?,
        val password2: String?,
        val loginUrl: String?,
        val memo: String?
)