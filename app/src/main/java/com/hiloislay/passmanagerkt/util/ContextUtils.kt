package com.hiloislay.passmanagerkt.util

import android.content.ClipData
import android.content.ClipboardManager
import org.apache.commons.lang3.ArrayUtils

object ContextUtils {

    /**
     * @param stringId フォーマットベースになるStringID
     * @param args フォーマットの引数になるStringID
     */
    fun formatString(stringId: Int, vararg args: Int): String {

        if (ArrayUtils.isEmpty(args)) {
            return com.hiloislay.passmanagerkt.PMApplication.getContext().getString(stringId)
        }
        val values = args.map { id -> com.hiloislay.passmanagerkt.PMApplication.getContext().getString(id) }

        return com.hiloislay.passmanagerkt.PMApplication.getContext().getString(stringId, *values.toTypedArray())

    }

    fun copyToClipBoard(str: String?) {
        str?.let { s ->
            val context = com.hiloislay.passmanagerkt.PMApplication.getContext();
            val cm = context.getSystemService(ClipboardManager::class.java)
            val clipData = ClipData.newPlainText(context.getString(com.hiloislay.passmanagerkt.R.string.clip_label), s)
            cm.primaryClip = clipData
        }
    }
}
