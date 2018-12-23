package com.yoidukigembu.passmanagerkt.presenter.passwordlist.impl

import com.yoidukigembu.passmanagerkt.model.holder.RepositoryHolder
import com.yoidukigembu.passmanagerkt.model.usecase.PasswordUseCase
import com.yoidukigembu.passmanagerkt.model.usecase.impl.PasswordUseCaseImpl
import com.yoidukigembu.passmanagerkt.presenter.BasePasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.EditPasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.impl.BasePasswordPresenterImpl
import com.yoidukigembu.passmanagerkt.util.Logger

class EditPasswordPresenterImpl(val processor: EditPasswordPresenter.FragmentProcessor) : BasePasswordPresenterImpl(), EditPasswordPresenter {

    private val usecase: PasswordUseCase = PasswordUseCaseImpl()

    override fun getProcessor(): BasePasswordPresenter.BasePasswordFragmentProcessor {
        return processor
    }

    override fun selectData(id: Long) {
        Logger.d("%s", id)
        val data = RepositoryHolder.passwordRepository.findById(id)
        Logger.d("%s", data)
        data?.let { processor.setInputData(it) }


    }

    override fun save() {
        Logger.d()
        val validateMessage = validate()
        if (!validateMessage.isNullOrBlank()) {
            processor.showToast(validateMessage!!)
            return
        }
        usecase.update(processor)
        processor.onSaveFinished()


    }


}

