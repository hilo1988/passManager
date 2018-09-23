package com.yoidukigembu.passmanagerkt.presenter.passwordlist

import com.yoidukigembu.passmanagerkt.model.usecase.PasswordUseCase
import com.yoidukigembu.passmanagerkt.model.usecase.impl.PasswordUseCaseImpl
import com.yoidukigembu.passmanagerkt.presenter.BasePasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.InputPasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.impl.BasePasswordPresenterImpl

class InputPasswordPresenterImpl(val processor: InputPasswordPresenter.FragmentProcessor) : BasePasswordPresenterImpl(), InputPasswordPresenter {

    private val usecase: PasswordUseCase = PasswordUseCaseImpl()

    override fun getProcessor(): BasePasswordPresenter.BasePasswordFragmentProcessor {
        return processor
    }

    override fun save() {
        val validateMessage = validate()
        if (!validateMessage.isNullOrBlank()) {
            processor.showToast(validateMessage!!)
            return
        }
        usecase.register(processor)
    }


}