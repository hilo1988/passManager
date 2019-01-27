package com.hiloislay.passmanagerkt.presenter

import com.hiloislay.passmanagerkt.presenter.impl.AppPasswordPresenterImpl
import com.hiloislay.passmanagerkt.presenter.impl.LoginPresenterImpl

object PresenterFactory {

    /**
     * @return アプリパスワードプレゼンタ
     */
    fun getAppPasswordPresenter(processor: AppPasswordPresenter.FragmentProcessor): AppPasswordPresenter =
            AppPasswordPresenterImpl(processor)

    /**
     * @return ログインプレゼンタ
     */
    fun getLoginPresenter(processor: LoginPresenter.FragmentProcessor) =
            LoginPresenterImpl(processor)
}