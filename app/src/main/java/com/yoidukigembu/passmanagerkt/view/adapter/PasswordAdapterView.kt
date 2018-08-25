package com.yoidukigembu.passmanagerkt.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.yoidukigembu.passmanagerkt.db.entity.Password
import kotlinx.android.synthetic.main.adapter_password_list.view.*

class PasswordAdapterView : RelativeLayout {

    @JvmOverloads constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0
    ):super(context, attrs, defStyleAttr)



    fun bindView(entity:Password) {
        passwordTitleText.text = entity.name
    }

}