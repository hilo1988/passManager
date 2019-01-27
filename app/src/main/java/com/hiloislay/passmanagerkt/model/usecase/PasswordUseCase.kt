package com.hiloislay.passmanagerkt.model.usecase

import com.hiloislay.passmanagerkt.accessor.PasswordDataAccessor
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
    fun register(accessor: PasswordDataAccessor): Single<Unit>

    fun update(accessor: PasswordDataAccessor)
}