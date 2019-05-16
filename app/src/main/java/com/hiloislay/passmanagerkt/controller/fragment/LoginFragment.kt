package com.hiloislay.passmanagerkt.controller.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.controller.fragment.dialog.FingerprintAuthenticationDialog
import com.hiloislay.passmanagerkt.databinding.FragmentAppPasswordBinding
import com.hiloislay.passmanagerkt.extension.toastShort
import com.hiloislay.passmanagerkt.model.usecase.impl.AppPasswordUseCaseImpl
import kotlinx.android.synthetic.main.fragment_app_password.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

/**
 * ログインフラグメント
 */
class LoginFragment : BaseFragment() {

    private val useCase = AppPasswordUseCaseImpl()

    var operator: ActivityOperator? = null

    private var binding: FragmentAppPasswordBinding? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_app_password, null, true)
        binding?.let {
            it.submitButton.setOnClickListener { onClickSubmitButton() }
            it.passwordConfEdit.visibility = View.GONE
        }

        return view!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myToolbar.setTitle(R.string.login)
        activity.setActionBar(myToolbar)

        fingerprintButton.setOnClickListener { showFingerPrintDialog() }
        showFingerPrintDialog()
    }


    private fun showFingerPrintDialog() {
        val dialog = FingerprintAuthenticationDialog.newInstance("default",
                {
                    Timber.d("")
                    operator?.onLogin()
                },
                { Timber.w("認証に失敗しました。") }
        )

        dialog.show(fragmentManager, FingerprintAuthenticationDialog::javaClass.name)
    }

    /**
     * サブミットボタン押下時
     */
    private fun onClickSubmitButton() {
        binding?.password1Edit?.text?.toString()
                ?.takeUnless { it.isEmpty() }
                ?.let {
                    if (useCase.isSamePassword(it)) {
                        operator?.onLogin()
                    } else {
                        toastShort(R.string.error_cannot_login)
                    }
                }
                ?: run {
                    toastShort(R.string.error_required,
                            R.string.password)
                }

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

}