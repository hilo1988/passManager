package com.yoidukigembu.passmanagerkt.controller.activity

import android.content.Intent
import android.os.Bundle
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.passwordinput.PasswordInputFragment
import com.yoidukigembu.passmanagerkt.controller.fragment.passwordlist.PasswordListFragment

class PasswordListActivity : BaseActivity(), PasswordListFragment.ActivityOperator,
        PasswordInputFragment.ActivityOperator {

    private val fragmentTag = "PasswordListActivity_container"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_password_list)

        fragmentManager.beginTransaction()
                .replace(R.id.container, PasswordListFragment.newInstance(this), fragmentTag)
                .commit()
    }


    override fun showAddFragment() {
        val i = Intent(applicationContext, PasswordInputActivity::class.java)
        i.putExtra(PasswordInputActivity.KEY_INPUT_TYPE, PasswordInputActivity.INPUT_TYPE_INPUT)
        startActivity(i)
    }
}