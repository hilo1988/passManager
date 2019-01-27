package com.hiloislay.passmanagerkt.presenter.impl

import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.model.usecase.impl.AppPasswordUseCaseImpl
import com.hiloislay.passmanagerkt.presenter.AppPasswordPresenter
import com.hiloislay.passmanagerkt.util.ContextUtils

class AppPasswordPresenterImpl(private val processor: AppPasswordPresenter.FragmentProcessor) : AppPasswordPresenter {


    private val useCase = AppPasswordUseCaseImpl()


    override fun createPassword(password: String, passwordConf: String) {
        if (!validate(password, passwordConf)) {
            return
        }


        if (useCase.save(password)) {
            processor.onPasswordCreated()
        } else {
            processor.onPasswordCreateFailure()
        }
    }

    private fun validate(password: String, passwordConf: String): Boolean {
        if (password.length < PASSWORD_LENGTH) {
            processor.showError(
                    ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.error_required, com.hiloislay.passmanagerkt.R.string.password))
            return false
        }

        if (password != passwordConf) {
            processor.showError(
                    ContextUtils.formatString(
                            com.hiloislay.passmanagerkt.R.string.error_notEquals,
                            com.hiloislay.passmanagerkt.R.string.password,
                            com.hiloislay.passmanagerkt.R.string.passwordConf))
            return false
        }

        return true
    }

    companion object {
        const val PASSWORD_LENGTH = 4
    }
}