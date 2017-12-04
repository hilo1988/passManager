package com.yoidukigembu.passmanagerkt.controller.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.presenter.AppPasswordPresenter
import com.yoidukigembu.passmanagerkt.presenter.PresenterFactory
import kotlinx.android.synthetic.main.fragment_app_password.*

class CreateAppPasswordFragment : BaseFragment(), AppPasswordPresenter.FragmentProcessor {

    private var operator: ActivityOperator? = null

    private val presenter = PresenterFactory.getAppPasswordPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater?.inflate(R.layout.fragment_app_password, null)


        return v!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener { v ->
            presenter.createPassword(
                    passwordEdit.text.toString(),
                    passwordConfEdit.text.toString())
        }
    }

    override fun onPasswordCreated() {
        operator?.showLogin()
    }

    override fun onPasswordCreateFailure() {
        Toast.makeText(context, R.string.error_passwordCreateFailed, Toast.LENGTH_SHORT).show()
    }

    override fun showError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    /**
     * アクティビティで処理するIF
     */
    interface ActivityOperator {
        /**
         * ログイン画面の表示
         */
        fun showLogin()
    }

    companion object {
        fun getInstance(operator: ActivityOperator): CreateAppPasswordFragment {
            val f = CreateAppPasswordFragment()
            f.operator = operator
            return f
        }
    }
}