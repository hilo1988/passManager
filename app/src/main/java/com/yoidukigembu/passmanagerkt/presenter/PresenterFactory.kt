package com.yoidukigembu.passmanagerkt.presenter

import com.yoidukigembu.passmanagerkt.presenter.impl.AppPasswordPresenterImpl
import com.yoidukigembu.passmanagerkt.presenter.impl.LoginPresenterImpl

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