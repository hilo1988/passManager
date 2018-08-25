package com.yoidukigembu.passmanagerkt.util

import android.content.ClipData
import android.content.ClipboardManager
import com.yoidukigembu.passmanagerkt.PMApplication
import com.yoidukigembu.passmanagerkt.R
import org.apache.commons.lang3.ArrayUtils

import java.util.ArrayList

object ContextUtils {

    /**
     * @param stringId フォーマットベースになるStringID
     * @param args フォーマットの引数になるStringID
     */
    fun formatString(stringId: Int, vararg args: Int): String {

        if (ArrayUtils.isEmpty(args)) {
            return PMApplication.getContext().getString(stringId)
        }
        val values = args.map { id -> PMApplication.getContext().getString(id) }

        return PMApplication.getContext().getString(stringId, *values.toTypedArray())

    }

    fun copyToClipBoard(str:String?) {
        str?.let { s ->
            val context = PMApplication.getContext();
            val cm = context.getSystemService(ClipboardManager::class.java)
            val clipData = ClipData.newPlainText(context.getString(R.string.clip_label), s)
            cm.primaryClip = clipData
        }
    }
}
