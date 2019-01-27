package com.hiloislay.passmanagerkt.controller.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiloislay.passmanagerkt.R

/**
 * アプリ用パスワードの基底フラグメント
 */
abstract class AppPasswordBaseFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(com.hiloislay.passmanagerkt.R.layout.fragment_app_password, null)


        return view!!
    }
}
