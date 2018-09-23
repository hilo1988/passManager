package com.yoidukigembu.passmanagerkt.model.usecase

import com.yoidukigembu.passmanagerkt.accessor.PasswordDataAccessor


/**
 * パスワードユースケース
 */
interface PasswordUseCase {


    fun createPasswordList()

    /**
     * パスワード登録
     * @return ID
     */
    fun register(accessor: PasswordDataAccessor): Long

    fun update(accessor: PasswordDataAccessor)
}