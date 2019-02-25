package com.hiloislay.passmanagerkt.presenter.impl

import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.model.usecase.impl.AppPasswordUseCaseImpl
import com.hiloislay.passmanagerkt.presenter.LoginPresenter
import com.hiloislay.passmanagerkt.util.ContextUtils

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