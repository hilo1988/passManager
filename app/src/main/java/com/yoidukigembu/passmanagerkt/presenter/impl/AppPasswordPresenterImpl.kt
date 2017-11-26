package com.yoidukigembu.passmanagerkt.presenter.impl

import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.model.usecase.impl.AppPasswordUseCaseImpl
import com.yoidukigembu.passmanagerkt.presenter.AppPasswordPresenter
import com.yoidukigembu.passmanagerkt.util.ContextUtils
import com.yoidukigembu.passmanagerkt.util.PasswordStrings

class AppPasswordPresenterImpl(private val processor: AppPasswordPresenter.FragmentProcessor) : AppPasswordPresenter {


    private val useCase= AppPasswordUseCaseImpl()


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

    private fun validate(password: String, passwordConf: String) : Boolean {
        if (password.length < PASSWORD_LENGTH) {
            processor.showError(
                    ContextUtils.formatString(R.string.error_required, R.string.password))
            return false
        }

        if (password != passwordConf) {
            processor.showError(
                    ContextUtils.formatString(
                            R.string.error_notEquals,
                            R.string.password,
                            R.string.passwordConf))
            return false
        }

        PasswordStrings.generatePasswordStr()

        return true
    }

    companion object {
        const val PASSWORD_LENGTH = 4
    }
}