package com.yoidukigembu.passmanagerkt.controller.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.functionalinterface.controller.fragment.dialog.OnMenuSelectedListener
import com.yoidukigembu.passmanagerkt.valueobject.MenuData
import com.yoidukigembu.passmanagerkt.view.adapter.MenuAdapter
import kotlinx.android.synthetic.main.dialog_list_menu.view.*


open class ListMenuDialogFragment : DialogFragment() {

    lateinit var mView: View

    lateinit var dataList: List<MenuData>

    lateinit var onMenuSelectedListener: OnMenuSelectedListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = View.inflate(context, R.layout.dialog_list_menu, null)
        val builder = AlertDialog.Builder(context);
        builder.setView(view)

        this.mView = view


        val listView = view.menuListView

        listView.adapter = MenuAdapter(context, dataList)
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, id ->
            onMenuSelectedListener.onMenuSelected(id)
            dismiss()
        }

        return builder.create()
    }


    companion object {
        fun newInstance(dataList: List<MenuData>, onMenuSelectedListener: OnMenuSelectedListener):
                ListMenuDialogFragment {

            val f = ListMenuDialogFragment()
            f.dataList = dataList
            f.onMenuSelectedListener = onMenuSelectedListener
            return f

        }
    }


}