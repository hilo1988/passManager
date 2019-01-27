package com.yoidukigembu.passmanagerkt.presenter.impl

import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.model.usecase.impl.AppPasswordUseCaseImpl
import com.yoidukigembu.passmanagerkt.presenter.LoginPresenter
import com.yoidukigembu.passmanagerkt.util.ContextUtils

class LoginPresenterImpl(private val processor: LoginPresenter.FragmentProcessor) : LoginPresenter {


    private val useCase = AppPasswordUseCaseImpl()

    override fun login(password: String) {

        if (password.isEmpty()) {
            processor.showError(ContextUtils.formatString(R.string.error_required,
                    R.string.password))
            return
        }

        if (useCase.isSamePassword(password)) {
            processor.onLoginSuccess()
        } else {
            processor.onLoginFailed()
        }
    }
}