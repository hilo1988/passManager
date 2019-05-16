package com.hiloislay.passmanagerkt.presenter.passwordlist.impl

import com.hiloislay.passmanagerkt.model.holder.RepositoryHolder
import com.hiloislay.passmanagerkt.model.usecase.PasswordUseCase
import com.hiloislay.passmanagerkt.model.usecase.impl.PasswordUseCaseImpl
import com.hiloislay.passmanagerkt.presenter.BasePasswordPresenter
import com.hiloislay.passmanagerkt.presenter.EditPasswordPresenter
import com.hiloislay.passmanagerkt.presenter.impl.BasePasswordPresenterImpl
import timber.log.Timber

class EditPasswordPresenterImpl(val processor: EditPasswordPresenter.FragmentProcessor) : BasePasswordPresenterImpl(), EditPasswordPresenter {

    private val usecase: PasswordUseCase = PasswordUseCaseImpl()

    override fun getProcessor(): BasePasswordPresenter.BasePasswordFragmentProcessor {
        return processor
    }

    override fun selectData(id: Long) {
        Timber.d("%s", id)
        val data = RepositoryHolder.passwordRepository.findById(id)
        Timber.d("%s", data)
        data?.let { processor.setInputData(it) }


    }

    override fun save() {
        Timber.d("")
        val validateMessage = validate()
        if (!validateMessage.isNullOrBlank()) {
            processor.showToast(validateMessage!!)
            return
        }
        usecase.update(processor)
        processor.onSaveFinished()


    }


}

