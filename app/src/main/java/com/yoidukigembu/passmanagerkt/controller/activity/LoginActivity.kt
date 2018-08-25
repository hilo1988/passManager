package com.yoidukigembu.passmanagerkt.controller.activity

import android.content.Intent
import android.os.Bundle
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.CreateAppPasswordFragment
import com.yoidukigembu.passmanagerkt.controller.fragment.LoginFragment
import com.yoidukigembu.passmanagerkt.model.usecase.impl.AppPasswordUseCaseImpl
import com.yoidukigembu.passmanagerkt.util.Logger

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
