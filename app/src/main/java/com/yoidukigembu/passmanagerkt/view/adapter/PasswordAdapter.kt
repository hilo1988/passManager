package com.yoidukigembu.passmanagerkt.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.github.gfx.android.orma.widget.OrmaListAdapter
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.db.entity.Password_Relation

class PasswordAdapter(context: Context, relation: Password_Relation) : OrmaListAdapter<Password>(context, relation) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: PasswordAdapterView =
                if (convertView != null) {
                    convertView as PasswordAdapterView
                } else {
                    View.inflate(context, R.layout.adapter_password_list, null) as PasswordAdapterView
                }

        view.bindView(getItem(position))

        return view;

    }
}