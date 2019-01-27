package com.hiloislay.passmanagerkt.presenter.passwordlist

import com.hiloislay.passmanagerkt.presenter.BasePresenter
import com.hiloislay.passmanagerkt.valueobject.RegisterData

interface BaseInputPresenter : BasePresenter {

    /**
     * パスワードの保存・更新
     */
    fun save(data: RegisterData)

    /**
     * フラグメント処理のI/F
     */
    interface InputFragmentProcessor : BasePresenter.BaseFragmentProcessor {

        /**
         * パスワードの登録・変更が終わった時にコール
         */
        fun onSaved()
    }
}