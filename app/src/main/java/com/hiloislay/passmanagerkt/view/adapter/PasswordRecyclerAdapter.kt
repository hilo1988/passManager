package com.hiloislay.passmanagerkt.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.databinding.AdapterPasswordListBinding
import com.hiloislay.passmanagerkt.db.realm.entity.Password
import com.hiloislay.passmanagerkt.viewmodel.PasswordListViewModel
import io.realm.RealmList

class PasswordRecyclerAdapter(val context: Context, var results: RealmList<Password>) : RecyclerView.Adapter<PasswordRecyclerAdapter.PasswordViewHolder>() {

    var onClickListener: ((entity: Password?) -> Unit)? = null

    var onLongClickListener: ((entity: Password?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordViewHolder {
        val inflater = LayoutInflater.from(context)
        return PasswordViewHolder(DataBindingUtil.inflate(inflater, R.layout.adapter_password_list, null, false))
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: PasswordViewHolder, position: Int) {
        holder.binding.let {
            if (it.viewModel == null) {
                it.viewModel = PasswordListViewModel.PasswordAdapterViewModel()
            }
            it.viewModel?.password = results[position]
            it.executePendingBindings()

            it.root.setOnClickListener{_ -> onClickListener?.let { cb -> cb(it.viewModel?.password) }}
            it.root.setOnLongClickListener { _ ->
                onLongClickListener?.let { cb -> cb(it.viewModel?.password)
                true} ?: run{ false }

            }
        }


    }

    override fun getItemId(position: Int): Long {
        return results[position]?.id ?: 0
    }

    class PasswordViewHolder(var binding: AdapterPasswordListBinding) : RecyclerView.ViewHolder(binding.root)

}