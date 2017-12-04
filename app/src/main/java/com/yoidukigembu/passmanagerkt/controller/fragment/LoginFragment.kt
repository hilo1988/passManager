package com.yoidukigembu.passmanagerkt.controller.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.presenter.LoginPresenter
import com.yoidukigembu.passmanagerkt.presenter.PresenterFactory
import kotlinx.android.synthetic.main.fragment_app_password.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * ログインフラグメント
 */
class LoginFragment : BaseFragment(), LoginPresenter.FragmentProcessor {

    private var operator : ActivityOperator? = null

    private val presenter = PresenterFactory.getLoginPresenter(this)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_app_password, null)


        return view!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener{_ -> onClickSubmitButton()}
        passwordConfEdit.visibility = View.GONE

        myToolbar.setTitle(R.string.login)
        activity.setActionBar(myToolbar)
    }


    private fun onClickSubmitButton() {
        val password = passwordEdit.text.toString()
        presenter.login(password)
    }


    override fun onLoginSuccess() {
        operator?.onLogin()
    }

    override fun onLoginFailed() {
        showError(getString(R.string.error_cannot_login))
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    /**
     * アクティビティで処理をするもの
     */
    interface ActivityOperator {

        /**
         * ログインに成功した時の処理
         */
        fun onLogin()
    }

    companion object {
        fun getInstance(operator: ActivityOperator) : LoginFragment {
            val f = LoginFragment()
            f.operator = operator
            return f
        }
    }

}