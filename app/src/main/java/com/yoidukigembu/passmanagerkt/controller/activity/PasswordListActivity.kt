package com.yoidukigembu.passmanagerkt.controller.activity

import android.os.Bundle
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.passwordlist.AddFragment
import com.yoidukigembu.passmanagerkt.controller.fragment.passwordlist.PasswordListFragment

class PasswordListActivity : BaseActivity(), PasswordListFragment.ActivityOperator,
        AddFragment.ActivityOperator {

    private val fragmentTag = "PasswordListActivity_container"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_password_list)

        fragmentManager.beginTransaction()
                .replace(R.id.container, PasswordListFragment.newInstance(this), fragmentTag)
                .commit()
    }


    override fun showAddFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, AddFragment.newInstance(this), fragmentTag)
                .addToBackStack(PasswordListFragment::javaClass.name)
                .commit()
    }
}