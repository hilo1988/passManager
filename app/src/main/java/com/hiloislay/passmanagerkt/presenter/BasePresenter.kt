package com.hiloislay.passmanagerkt.presenter

import android.app.DialogFragment
import android.content.Context
import io.reactivex.disposables.CompositeDisposable

/**
 * 基底プレゼンタ
 */
interface BasePresenter {


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
        fun getContext(): Context

        fun getDisposable(): CompositeDisposable?
    }

}