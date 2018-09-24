package com.yoidukigembu.passmanagerkt.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.valueobject.MenuData

class MenuAdapter(val context: Context,
                  private val menuList: List<MenuData>) : BaseAdapter() {


    fun getElement(position: Int) = menuList[position]

    override fun getItem(position: Int) = getElement(position)

    override fun getItemId(position: Int) = getElement(position).id


    override fun getCount() = menuList.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: MenuAdapterView


        if (convertView == null) {
            view = View.inflate(context, R.layout.adapter_menu, null) as MenuAdapterView
        } else {
            view = convertView as MenuAdapterView
        }

        return view.apply { bind(getElement(position)) }
    }
}