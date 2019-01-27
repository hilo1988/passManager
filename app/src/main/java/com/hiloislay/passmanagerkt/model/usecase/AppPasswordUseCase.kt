package com.hiloislay.passmanagerkt.model.usecase

/**
 * アプリ本体のパスワード用useCase
 * ※ 管理をするパスワードではない
 */
interface AppPasswordUseCase {


    /**
     * パスワードの保存
     * @param password 保存するパスワード(未暗号化
     * @return 保存に成功した場合にtrue
     */
    fun save(password: String): Boolean

    /**
     * アプリパスワードが保存されているかどうか
     * @return されていればtrue
     */
    fun existsAppPassword(): Boolean


    /**
     * アプリパスワードと引数のパスワードが同じかどうか
     * @param password パスワード(未暗号化
     * @return 一致する場合にtrue
     */
    fun isSamePassword(password: String): Boolean
}
