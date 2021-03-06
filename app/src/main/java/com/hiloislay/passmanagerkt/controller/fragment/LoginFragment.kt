package com.hiloislay.passmanagerkt.controller.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.controller.fragment.dialog.FingerprintAuthenticationDialog
import com.hiloislay.passmanagerkt.presenter.LoginPresenter
import com.hiloislay.passmanagerkt.presenter.PresenterFactory
import kotlinx.android.synthetic.main.fragment_app_password.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * ログインフラグメント
 */
class LoginFragment : BaseFragment(), LoginPresenter.FragmentProcessor {

    private var operator: ActivityOperator? = null

    private val presenter = PresenterFactory.getLoginPresenter(this)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_app_password, null)
        return view!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener { onClickSubmitButton() }
        passwordConfEdit.visibility = View.GONE

        myToolbar.setTitle(R.string.login)
        activity.setActionBar(myToolbar)

        fingerprintButton.setOnClickListener { showFingerPrintDialog() }
        showFingerPrintDialog()
    }


    private fun showFingerPrintDialog() {
        val dialog = FingerprintAuthenticationDialog.newInstance("default",
                {
                    com.hiloislay.passmanagerkt.util.Logger.d()
                    operator?.onLogin()
                },
                { com.hiloislay.passmanagerkt.util.Logger.w("認証に失敗しました。") }
        )

        dialog.show(fragmentManager, FingerprintAuthenticationDialog::javaClass.name)
    }

    /**
     * サブミットボタン押下時
     */
    private fun onClickSubmitButton() {
        val password = password1Edit.text.toString()
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
        fun getInstance(operator: ActivityOperator): LoginFragment {
            val f = LoginFragment()
            f.operator = operator
            return f
        }
    }

}