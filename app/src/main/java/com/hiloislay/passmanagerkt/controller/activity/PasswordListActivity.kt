package com.hiloislay.passmanagerkt.controller.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.controller.fragment.passwordinput.PasswordInputFragment
import com.hiloislay.passmanagerkt.controller.fragment.passwordlist.PasswordDetailFragment
import com.hiloislay.passmanagerkt.controller.fragment.passwordlist.PasswordListFragment
import com.hiloislay.passmanagerkt.db.realm.entity.Password

class PasswordListActivity : BaseActivity(), PasswordListFragment.ActivityOperator,
        PasswordInputFragment.ActivityOperator, PasswordDetailFragment.ActivityOperator {

    private val fragmentTag = "PasswordListActivity_container"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(com.hiloislay.passmanagerkt.R.layout.activity_password_list)

        fragmentManager.beginTransaction()
                .replace(com.hiloislay.passmanagerkt.R.id.container, PasswordListFragment.newInstance(this), fragmentTag)
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
                .replace(com.hiloislay.passmanagerkt.R.id.container, f, fragmentTag)
                .addToBackStack("")
                .commit()
    }
}