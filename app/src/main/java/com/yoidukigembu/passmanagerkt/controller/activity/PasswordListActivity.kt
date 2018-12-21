package com.yoidukigembu.passmanagerkt.controller.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.passwordinput.PasswordInputFragment
import com.yoidukigembu.passmanagerkt.controller.fragment.passwordlist.PasswordDetailFragment
import com.yoidukigembu.passmanagerkt.controller.fragment.passwordlist.PasswordListFragment
import com.yoidukigembu.passmanagerkt.db.entity.Password

class PasswordListActivity : BaseActivity(), PasswordListFragment.ActivityOperator,
        PasswordInputFragment.ActivityOperator, PasswordDetailFragment.ActivityOperator {

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

    override fun showEditFragment(id: Long) {
        val i = Intent(applicationContext, PasswordInputActivity::class.java)
        i.putExtra(PasswordInputActivity.KEY_INPUT_TYPE, PasswordInputActivity.INPUT_TYPE_EDIT)
        i.putExtra(PasswordInputActivity.KEY_PASSWORD_ID, id)
        startActivity(i)
    }

    override fun openUrl(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun showDetailFragment(entity: Password) {
        val f = PasswordDetailFragment.getInstance(entity, this)
        fragmentManager.beginTransaction()
                .replace(R.id.container, f, fragmentTag)
                .addToBackStack("")
                .commit()
    }
}