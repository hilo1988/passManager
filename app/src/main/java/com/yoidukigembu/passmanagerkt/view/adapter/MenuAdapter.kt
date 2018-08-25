package com.yoidukigembu.passmanagerkt.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.valueobject.MenuData

class MenuAdapter(context: Context, list: List<MenuData>) : ArrayAdapter<MenuData>(context, R.layout.dialog_list_menu, list) {

    val dataList = list


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById(R.id.menuText) as TextView
        textView.text = dataList[position].name

        return view

    }

    override fun getItemId(position: Int): Long {
        return dataList[position].id
    }

}