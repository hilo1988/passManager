package com.yoidukigembu.passmanagerkt.controller.fragment.passwordinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.db.realm.entity.Password
import com.yoidukigembu.passmanagerkt.presenter.BasePasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.EditPasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.passwordlist.impl.EditPasswordPresenterImpl
import com.yoidukigembu.passmanagerkt.valueobject.Cryptor
import kotlinx.android.synthetic.main.fragment_password_input.*

class PasswordEditFragment : BaseInputPasswordFragment(), EditPasswordPresenter.FragmentProcessor {


    var operator: ActivityOperator? = null

    var passwordId: Long? = null

    private val presenter: EditPasswordPresenter = EditPasswordPresenterImpl(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_password_input, null)
        return view!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener { presenter.submit() }
        password1GenerateButton.setOnClickListener {
            presenter.createPasswordGenerateDialog(BasePasswordPresenter.PasswordType.FIRST)
        }
        password2GenerateButton.setOnClickListener {
            presenter.createPasswordGenerateDialog(BasePasswordPresenter.PasswordType.SECOND)
        }

        passwordId?.let { presenter.selectData(it) }

    }

    override fun onSaveFinished() {
        operator?.finish()
    }

    override fun setInputData(entity: Password) {
        val cryptor = Cryptor.getInstance()
        entity.name?.let { labelName.setText(it) }
        entity.loginId?.let { loginId.setText(it) }
        entity.password1?.let { password1Edit.setText(cryptor.decrypt(it)) }
        entity.password2?.let { password2Edit.setText(cryptor.decrypt(it)) }
        entity.loginUrl?.let { loginUrlEdit.setText(it) }
        entity.memo?.let { memoEdit.setText(it) }

    }

    override fun getPrimaryId() = passwordId

    interface ActivityOperator : BaseActivityOperator {
        fun finish()
    }
}