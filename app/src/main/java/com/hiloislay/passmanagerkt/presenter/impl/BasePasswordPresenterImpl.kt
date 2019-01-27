package com.hiloislay.passmanagerkt.presenter.impl

import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.controller.fragment.dialog.PasswordGeneratorDialogFragment
import com.hiloislay.passmanagerkt.presenter.BasePasswordPresenter
import com.hiloislay.passmanagerkt.util.ContextUtils
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BasePasswordPresenterImpl : BasePresenterImpl(), BasePasswordPresenter {


    abstract fun getProcessor(): BasePasswordPresenter.BasePasswordFragmentProcessor

    /**
     * 登録・変更
     */
    abstract fun save()

    override fun submit() {

        val validateMsg = validate()
        if (!validateMsg.isNullOrBlank()) {
            getProcessor().showToast(validateMsg!!)
            return
        }

        save()
        Single
                .create { s: SingleEmitter<Any> ->

                    s.onSuccess(1)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { e -> e.message.let { msg -> getProcessor().showToast(msg!!) } }
                .subscribe { _ -> getProcessor().onSaveFinished() }
    }

    override fun createPasswordGenerateDialog(type: BasePasswordPresenter.PasswordType) {
        val f = PasswordGeneratorDialogFragment.newInstance {
            if (type == BasePasswordPresenter.PasswordType.FIRST) {
                getProcessor().setPassword1(it)
            } else {
                getProcessor().setPassword2(it)
            }
        }

        getProcessor().showDialog(f, PasswordGeneratorDialogFragment::class.java.name)
    }

    fun validate(): String? {
        val processor = getProcessor()
        if (processor.getLoginId().isNullOrBlank()) {
            return ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.error_required, com.hiloislay.passmanagerkt.R.string.labelName)
        }

        if (processor.getPassword1().isNullOrBlank()) {
            return ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.error_required, com.hiloislay.passmanagerkt.R.string.password)
        }

        return null
    }


}