package com.yoidukigembu.passmanagerkt.util

import com.yoidukigembu.passmanagerkt.PMApplication
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
}
