package com.yoidukigembu.passmanagerkt.controller.fragment.passwordinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.presenter.BasePasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.InputPasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.passwordlist.InputPasswordPresenterImpl
import kotlinx.android.synthetic.main.fragment_password_input.*


/**
 * パスワード登録フラグメント
 */
class PasswordInputFragment : BaseInputPasswordFragment(), InputPasswordPresenter.FragmentProcessor {

    private lateinit var operator: ActivityOperator

    private val presenter: InputPasswordPresenter = InputPasswordPresenterImpl(this)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_password_input, null)
        return view!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener { _ -> presenter.submit() }
        password1GenerateButton.setOnClickListener { _ ->
            presenter.createPasswordGenerateDialog(BasePasswordPresenter.PasswordType.FIRST)
        }
        password2GenerateButton.setOnClickListener { _ ->
            presenter.createPasswordGenerateDialog(BasePasswordPresenter.PasswordType.SECOND)
        }
    }

    companion object {
        fun newInstance(operator: ActivityOperator): PasswordInputFragment {
            val f = PasswordInputFragment()
            f.operator = operator
            return f
        }
    }


    override fun getPrimaryId(): Long? {
        return null
    }

    override fun onSaveFinished() {
        operator.finish()
    }

    interface ActivityOperator : BaseActivityOperator {
        fun finish()
    }
}