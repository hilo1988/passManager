package com.hiloislay.passmanagerkt.presenter

import android.content.Context
import android.support.v4.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable

/**
 * 基底プレゼンタ
 */
interface BasePresenter {


    fun onDestroy() {}

    interface BaseFragmentProcessor {
        /**
         * トーストの表示
         */
        fun showToast(str: String)

        /**
         * トーストの表示
         */
        fun showToast(stringRes: Int)

        /**
         * ダイアログ表示
         */
        fun showDialog(dialogFragment: DialogFragment, tag: String)

        /**
         * コンテキスト取得
         */
        fun getContext(): Context?

        fun getDisposable(): CompositeDisposable?
    }

}