package com.hiloislay.passmanagerkt.view.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.hiloislay.passmanagerkt.db.realm.entity.Password
import kotlinx.android.synthetic.main.adapter_password_list.view.*

class PasswordAdapterView : ConstraintLayout {

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0
    ) : super(context, attrs, defStyleAttr)


    fun bindView(entity: Password) {
        passwordTitleText.text = entity.name
    }

}