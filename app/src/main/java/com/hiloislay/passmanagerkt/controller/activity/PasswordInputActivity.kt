package com.hiloislay.passmanagerkt.controller.activity

import android.app.Fragment
import android.os.Bundle
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.controller.fragment.passwordinput.PasswordEditFragment
import com.hiloislay.passmanagerkt.controller.fragment.passwordinput.PasswordInputFragment

class PasswordInputActivity : BaseActivity(), PasswordInputFragment.ActivityOperator, PasswordEditFragment.ActivityOperator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.hiloislay.passmanagerkt.R.layout.activity_password_input)

        val inputType = intent.extras.getInt(KEY_INPUT_TYPE)


        val f: Fragment

        if (inputType == INPUT_TYPE_INPUT) {
            f = PasswordInputFragment.newInstance(this)

        } else {
            val passwordId = intent.extras.getLong(KEY_PASSWORD_ID)
            f = PasswordEditFragment().also {
                it.passwordId = passwordId
                it.operator = this
            }
        }

        fragmentManager.beginTransaction()
                .replace(com.hiloislay.passmanagerkt.R.id.container, f)
                .commit()
    }

    companion object {
        val INPUT_TYPE_INPUT = 1
        val INPUT_TYPE_EDIT = 2
        val KEY_INPUT_TYPE = "KEY_INPUT_TYPE"
        val KEY_PASSWORD_ID = "KEY_PASSWORD_ID"
    }
}