package com.yoidukigembu.passmanagerkt.presenter.impl

import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.dialog.PasswordGeneratorDialogFragment
import com.yoidukigembu.passmanagerkt.functionalinterface.controller.fragment.dialog.OnPasswordGeneratedListener
import com.yoidukigembu.passmanagerkt.presenter.BasePasswordPresenter
import com.yoidukigembu.passmanagerkt.util.ContextUtils
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
        Single
                .create { s: SingleEmitter<Any> ->
                    save()
                    s.onSuccess(1)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { e -> e.message.let { msg -> getProcessor().showToast(msg!!) } }
                .subscribe { _ -> getProcessor().onSaveFinished() }
    }

    override fun createPasswordGenerateDialog(type: BasePasswordPresenter.PasswordType) {
        val f = PasswordGeneratorDialogFragment.newInstance(OnPasswordGeneratedListener {
            if (type == BasePasswordPresenter.PasswordType.FIRST) {
                getProcessor().setPassword1(it)
            } else {
                getProcessor().setPassword2(it)
            }
        })

        getProcessor().showDialog(f, PasswordGeneratorDialogFragment::class.java.name)
    }

    fun validate(): String? {
        val processor = getProcessor()
        if (processor.getLoginId().isNullOrBlank()) {
            return ContextUtils.formatString(R.string.error_required, R.string.labelName)
        }

        if (processor.getPassword1().isNullOrBlank()) {
            return ContextUtils.formatString(R.string.error_required, R.string.password)
        }

        return null
    }


}