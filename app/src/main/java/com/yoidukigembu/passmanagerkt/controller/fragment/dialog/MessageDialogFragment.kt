package com.yoidukigembu.passmanagerkt.controller.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle

/**
 * メッセージを表示するダイアログ
 */
class MessageDialogFragment : DialogFragment() {

    lateinit var messageData: MessageData

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)

        messageData.title?.let { s -> builder.setTitle(s) }

        builder.setMessage(messageData.message)

        builder.setPositiveButton(messageData.positiveTitle, messageData.positiveListener)

        messageData.negativeTitle?.let { title -> builder.setNegativeButton(title, messageData.negativeListener) }

        return super.onCreateDialog(savedInstanceState)
    }


    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        messageData.dismissCallback?.let { it() }
    }

    companion object {

        fun newInstance(messageData: MessageData): MessageDialogFragment {
            val dialog = MessageDialogFragment()
            dialog.messageData = messageData
            return dialog
        }


        /**
         * ダイアログを表示するためのデータクラス
         */
        class MessageData(var message: CharSequence, var positiveTitle: CharSequence = "OK") {
            var title: String? = null

            var positiveListener: DialogInterface.OnClickListener? = null

            var negativeTitle: CharSequence? = null

            var negativeListener: DialogInterface.OnClickListener? = null

            var dismissCallback: (() -> Unit)? = null

        }
    }
}