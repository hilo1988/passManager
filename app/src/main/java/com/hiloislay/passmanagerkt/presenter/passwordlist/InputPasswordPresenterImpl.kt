package com.hiloislay.passmanagerkt.presenter.passwordlist

import com.hiloislay.passmanagerkt.model.usecase.PasswordUseCase
import com.hiloislay.passmanagerkt.model.usecase.impl.PasswordUseCaseImpl
import com.hiloislay.passmanagerkt.presenter.BasePasswordPresenter
import com.hiloislay.passmanagerkt.presenter.InputPasswordPresenter
import com.hiloislay.passmanagerkt.presenter.impl.BasePasswordPresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class InputPasswordPresenterImpl(val processor: InputPasswordPresenter.FragmentProcessor) : BasePasswordPresenterImpl(), InputPasswordPresenter {

    private val usecase: PasswordUseCase = PasswordUseCaseImpl()

    override fun getProcessor(): BasePasswordPresenter.BasePasswordFragmentProcessor {
        return processor
    }

    override fun save() {
        Timber.d("")
        val validateMessage = validate()
        if (!validateMessage.isNullOrBlank()) {
            processor.showToast(validateMessage!!)
            return
        }
        usecase.register(processor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ processor.onSaveFinished() }, {})
                .apply { processor.getDisposable()?.add(this) }

    }


}