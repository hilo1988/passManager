package com.hiloislay.passmanagerkt.controller.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.util.ContextUtils
import com.hiloislay.passmanagerkt.util.Logger
import com.hiloislay.passmanagerkt.util.PasswordStrings
import kotlinx.android.synthetic.main.dialog_password_generator.view.*
import org.apache.commons.lang3.math.NumberUtils


/**
 * パスワード生成ダイアログ
 */
class PasswordGeneratorDialogFragment : DialogFragment() {

    private lateinit var mView: View

    private var onPasswordGeneratedListener: ((String) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = View.inflate(context, com.hiloislay.passmanagerkt.R.layout.dialog_password_generator, null)
        com.hiloislay.passmanagerkt.util.Logger.d()
        val builder = AlertDialog.Builder(context)
        builder.setView(view!!)

        mView = view
        mView.cancelButton.setOnClickListener { _ -> dismiss() }
        mView.generateButton.setOnClickListener { _ -> generatePassword() }


        return builder.create()
    }


    private fun generatePassword() {
        com.hiloislay.passmanagerkt.util.Logger.d()
        val lengthStr = mView.numberSizeText.text.toString()
        if (!NumberUtils.isDigits(lengthStr)) {
            Toast.makeText(context,
                    ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.error_required, com.hiloislay.passmanagerkt.R.string.numberSize),
                    Toast.LENGTH_SHORT)
                    .show()
            return
        }

        val length = lengthStr.toInt()
        if (length <= 0 || length > 99) {
            Toast.makeText(context,
                    com.hiloislay.passmanagerkt.R.string.error_numberSizeLength,
                    Toast.LENGTH_SHORT)
                    .show()
            return
        }

        if (!mView.smallAlphabetCheckBox.isChecked
                && !mView.bigAlphabetCheckBox.isChecked
                && !mView.numberLetterCheckBox.isChecked
                && !mView.symbolLetterCheckBox.isChecked) {
            Toast.makeText(context,
                    ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.error_noSelected, com.hiloislay.passmanagerkt.R.string.letterType),
                    Toast.LENGTH_SHORT)
                    .show()
            return
        }
        val password = PasswordStrings.generatePasswordStr(mView.numberSizeText.text.toString().toInt(),
                mView.smallAlphabetCheckBox.isChecked,
                mView.bigAlphabetCheckBox.isChecked,
                mView.numberLetterCheckBox.isChecked,
                mView.symbolLetterCheckBox.isChecked)

        com.hiloislay.passmanagerkt.util.Logger.d("generated passowrd:[%s]", password)

        onPasswordGeneratedListener?.let { it(password) }
        dismiss()
    }


    companion object {
        fun newInstance(onPasswordGeneratedListener: ((String) -> Unit)): PasswordGeneratorDialogFragment {
            val f = PasswordGeneratorDialogFragment()
            f.onPasswordGeneratedListener = onPasswordGeneratedListener
            return f
        }
    }
}