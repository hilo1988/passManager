package com.yoidukigembu.passmanagerkt.presenter

import com.yoidukigembu.passmanagerkt.db.realm.entity.Password

interface EditPasswordPresenter : BasePasswordPresenter {


    fun selectData(id: Long)

    interface FragmentProcessor : BasePasswordPresenter.BasePasswordFragmentProcessor {
        fun setInputData(entity: Password)
    }
}