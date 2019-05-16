package com.hiloislay.passmanagerkt.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.support.v4.app.Fragment
import com.hiloislay.passmanagerkt.db.realm.entity.Password
import com.hiloislay.passmanagerkt.view.adapter.PasswordRecyclerAdapter
import io.realm.RealmList

class PasswordListViewModel(private val fragment: Fragment, var adapter: PasswordRecyclerAdapter) : ViewModel() {

    var list = MutableLiveData<RealmList<Password>>()
            .also {
                it.observe(fragment, Observer {
                    it?.let { list ->
                        adapter.results = list
                        adapter.notifyDataSetChanged()
                    }
                })
            }

    class PasswordAdapterViewModel : ViewModel() {

        var password: Password? = null
            set(value) {
                field = value
                name = value?.name ?: ""
            }

        var name = ""
    }

    fun copyPassword() {

    }
}