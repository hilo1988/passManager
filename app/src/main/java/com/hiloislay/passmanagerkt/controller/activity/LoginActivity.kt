package com.hiloislay.passmanagerkt.controller.activity

import android.content.Intent
import android.os.Bundle
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.controller.fragment.CreateAppPasswordFragment
import com.hiloislay.passmanagerkt.controller.fragment.LoginFragment
import com.hiloislay.passmanagerkt.model.usecase.impl.AppPasswordUseCaseImpl
import com.hiloislay.passmanagerkt.util.Logger

class LoginActivity : BaseActivity(), LoginFragment.ActivityOperator, CreateAppPasswordFragment.ActivityOperator {

    private val passwordUseCase = AppPasswordUseCaseImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragment =
                if (!passwordUseCase.existsAppPassword())
                    CreateAppPasswordFragment.getInstance(this)
                else
                    LoginFragment.getInstance(this)

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    override fun showLogin() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.getInstance(this))
                .commit()
    }

    override fun onLogin() {
        Logger.v()
        startActivity(Intent(this, PasswordListActivity::class.java))
    }
}
