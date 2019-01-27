package com.hiloislay.passmanagerkt.controller.fragment.passwordinput

import com.hiloislay.passmanagerkt.controller.fragment.BaseFragment
import com.hiloislay.passmanagerkt.presenter.BasePasswordPresenter
import kotlinx.android.synthetic.main.fragment_password_input.*

abstract class BaseInputPasswordFragment : BaseFragment(), BasePasswordPresenter.BasePasswordFragmentProcessor {


    override fun getLabelName(): String? {
        return labelName.text.toString()
    }

    override fun getLoginId(): String? {
        return loginId.text.toString()
    }

    override fun getPassword1(): String? {
        return password1Edit.text.toString()
    }

    override fun getPassword2(): String? {
        return password2Edit.text.toString()
    }

    override fun getLoginUrl(): String? {
        return loginUrlEdit.text.toString()
    }

    override fun getMemo(): String? {
        return memoEdit.text.toString()
    }


    override fun setPassword1(password: String) {
        password1Edit.setText(password)
    }

    override fun setPassword2(password: String) {
        password2Edit.setText(password)
    }
}