package com.yoidukigembu.passmanagerkt.model.usecase

import com.yoidukigembu.passmanagerkt.accessor.PasswordDataAccessor
import io.reactivex.Single


/**
 * パスワードユースケース
 */
interface PasswordUseCase {


    fun createPasswordList()

    /**
     * パスワード登録
     * @return ID
     */
    fun register(accessor: PasswordDataAccessor): Single<Long>

    fun update(accessor: PasswordDataAccessor)
}