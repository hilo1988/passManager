package com.yoidukigembu.passmanagerkt.controller.fragment.passwordlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.BaseFragment
import com.yoidukigembu.passmanagerkt.db.realm.entity.Password
import com.yoidukigembu.passmanagerkt.valueobject.Cryptor
import com.yoidukigembu.passmanagerkt.valueobject.SecretlyTextData
import kotlinx.android.synthetic.main.fragment_password_detail.*

class PasswordDetailFragment : BaseFragment() {

    lateinit var entity: Password

    lateinit var operator: ActivityOperator

    var passwordData1: SecretlyTextData? = null

    var passwordData2: SecretlyTextData? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_password_detail, null)

        return view!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        labelNameText.text = entity.name

        loginIdGroup.visibility = entity.loginId?.run {
            loginIdText.text = this
            View.VISIBLE
        } ?: View.GONE

        entity.password1?.apply {
            passwordData1 = SecretlyTextData(password1Text, Cryptor.getInstance().decrypt(this))
            password1ToggleButton.setOnClickListener { passwordData1?.toggleHidden() }
        }



        password2Group.visibility = entity.password2?.let {
            passwordData2 = SecretlyTextData(password2Text, Cryptor.getInstance().decrypt(it))
            password2ToggleButton.setOnClickListener { passwordData2?.toggleHidden() }
            View.VISIBLE
        } ?: View.GONE

        loginUrlGroup.visibility = entity.loginUrl?.run {
            loginUrlText.text = this
            View.VISIBLE
        } ?: View.GONE

        memoGroup.visibility = entity.memo?.run {
            memoText.text = this
            View.VISIBLE
        } ?: View.GONE


    }

    interface ActivityOperator

    companion object {

        fun getInstance(entity: Password, operator: ActivityOperator): PasswordDetailFragment {
            val f = PasswordDetailFragment()
            f.entity = entity
            f.operator = operator
            return f
        }
    }
}
