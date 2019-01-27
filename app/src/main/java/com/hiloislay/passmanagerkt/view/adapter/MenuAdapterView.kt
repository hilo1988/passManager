package com.hiloislay.passmanagerkt.view.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.hiloislay.passmanagerkt.valueobject.MenuData
import kotlinx.android.synthetic.main.adapter_menu.view.*

class MenuAdapterView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun bind(menu: MenuData) {
        menuText.text = menu.name
    }

}