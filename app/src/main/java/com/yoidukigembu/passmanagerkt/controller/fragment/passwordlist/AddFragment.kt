package com.yoidukigembu.passmanagerkt.controller.fragment.passwordlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.BaseFragment

class AddFragment : BaseFragment() {

    private lateinit var operator: ActivityOperator


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_password_input, null)
        
    }

    companion object {
        fun newInstance(operator: ActivityOperator): AddFragment {
            val f = AddFragment()
            f.operator = operator
            return f
        }
    }

    interface ActivityOperator : BaseActivityOperator {

    }
}