package com.yoidukigembu.passmanager.utils

import com.yoidukigembu.passmanagerkt.PMApplication


/**
 * コンテキストユーティリティ
 */
object ContextUtils {

    /**
     * @param stringId フォーマットベースになるStringID
     * @param args フォーマットの引数になるStringID
     */
    fun formatString(stringId:Int, vararg args:Int) : String {
        val context = PMApplication.getContext()
        val strings = args.map { s -> context.getString(s) }
        return context.getString(stringId, strings.toTypedArray())
    }
}