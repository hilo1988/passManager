package com.hiloislay.passmanagerkt.controller.activity

import android.content.Intent
import android.os.Bundle
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.controller.fragment.CreateAppPasswordFragment
import com.hiloislay.passmanagerkt.controller.fragment.LoginFragment
import com.hiloislay.passmanagerkt.model.usecase.impl.AppPasswordUseCaseImpl
import timber.log.Timber

class LoginActivity : BaseActivity(), LoginFragment.ActivityOperator, CreateAppPasswordFragment.ActivityOperator {

    private val passwordUseCase = AppPasswordUseCaseImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragment =
                if (!passwordUseCase.existsAppPassword())
                    CreateAppPasswordFragment().also { it.operator = this }
                else
                    LoginFragment().also { it.operator = this }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    override fun showLogin() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment().also { it.operator = this })
                .commit()
    }

    override fun onLogin() {
        Timber.v("")
        startActivity(Intent(this, PasswordListActivity::class.java))
    }
}
