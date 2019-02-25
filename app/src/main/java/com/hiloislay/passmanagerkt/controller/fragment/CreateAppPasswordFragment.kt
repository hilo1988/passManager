package com.hiloislay.passmanagerkt.controller.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.presenter.AppPasswordPresenter
import com.hiloislay.passmanagerkt.presenter.PresenterFactory
import kotlinx.android.synthetic.main.fragment_app_password.*
import kotlinx.android.synthetic.main.toolbar.*

class CreateAppPasswordFragment : BaseFragment(), AppPasswordPresenter.FragmentProcessor {

    private var operator: ActivityOperator? = null

    private val presenter = PresenterFactory.getAppPasswordPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater?.inflate(R.layout.fragment_app_password, null)


        return v!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener {
            presenter.createPassword(
                    password1Edit.text.toString(),
                    passwordConfEdit.text.toString())
        }

        myToolbar.setTitle(R.string.app_password_registration)
        activity.setActionBar(myToolbar)
        fingerprintButton.visibility = View.GONE
        registerPasswordText.visibility = View.VISIBLE

        submitButton.setText(R.string.submit)

    }

    override fun onPasswordCreated() {
        Toast.makeText(context, R.string.app_password_is_registered, Toast.LENGTH_SHORT).show()
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