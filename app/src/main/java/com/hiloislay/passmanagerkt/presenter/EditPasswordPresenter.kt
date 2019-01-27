package com.hiloislay.passmanagerkt.presenter

import com.hiloislay.passmanagerkt.db.realm.entity.Password

interface EditPasswordPresenter : BasePasswordPresenter {


    fun selectData(id: Long)

    interface FragmentProcessor : BasePasswordPresenter.BasePasswordFragmentProcessor {
        fun setInputData(entity: Password)
    }
}