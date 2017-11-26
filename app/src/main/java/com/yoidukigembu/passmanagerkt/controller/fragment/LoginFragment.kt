package com.yoidukigembu.passmanagerkt.controller.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.presenter.LoginPresenter
import kotlinx.android.synthetic.main.fragment_app_password.*

/**
 * ログインフラグメント
 */
class LoginFragment : BaseFragment(), LoginPresenter.FragmentProcessor {

    private var operator : ActivityOperator? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_app_password, null)

//        submitButton.setOnClickListener{v -> onClickSubmitButton(v)}
//        passwordConfEdit.visibility = View.GONE

        return view!!
    }


    private fun onClickSubmitButton(v : View?) {
        passwordEdit.text.toString()
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