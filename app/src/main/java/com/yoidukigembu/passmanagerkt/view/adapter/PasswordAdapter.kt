package com.yoidukigembu.passmanagerkt.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.db.realm.entity.Password

class PasswordAdapter(val context: Context, var results: List<Password>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return results[position]
    }

    override fun getCount(): Int {
        return results.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: PasswordAdapterView =
                if (convertView != null) {
                    convertView as PasswordAdapterView
                } else {
                    View.inflate(context, R.layout.adapter_password_list, null) as PasswordAdapterView
                }

        view.bindView(getItem(position) as Password)

        return view

    }

    override fun getItemId(position: Int): Long {
        return (getItem(position) as Password).id
    }
}