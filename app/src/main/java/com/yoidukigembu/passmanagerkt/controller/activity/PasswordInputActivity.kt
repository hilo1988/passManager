package com.yoidukigembu.passmanagerkt.controller.activity

import android.os.Bundle
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.passwordinput.PasswordInputFragment

class PasswordInputActivity : BaseActivity(), PasswordInputFragment.ActivityOperator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_input)

        val inputType = intent.extras.getInt(KEY_INPUT_TYPE)


        if (inputType == INPUT_TYPE_INPUT) {
            val f = PasswordInputFragment.newInstance(this)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, f)
                    .commit()
        }
    }

    companion object {
        val INPUT_TYPE_INPUT = 1
        val INPUT_TYPE_EDIT = 2
        val KEY_INPUT_TYPE = "KEY_INPUT_TYPE"
        val KEY_PASSWORD_ID = "KEY_PASSWORD_ID"
    }
}