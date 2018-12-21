package com.yoidukigembu.passmanagerkt.controller.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.util.ContextUtils
import com.yoidukigembu.passmanagerkt.util.Logger
import com.yoidukigembu.passmanagerkt.util.PasswordStrings
import kotlinx.android.synthetic.main.dialog_password_generator.view.*
import org.apache.commons.lang3.math.NumberUtils


/**
 * パスワード生成ダイアログ
 */
class PasswordGeneratorDialogFragment : DialogFragment() {

    private lateinit var mView: View

    private var onPasswordGeneratedListener: ((String) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = View.inflate(context, R.layout.dialog_password_generator, null)
        Logger.d()
        val builder = AlertDialog.Builder(context)
        builder.setView(view!!)

        mView = view
        mView.cancelButton.setOnClickListener { _ -> dismiss() }
        mView.generateButton.setOnClickListener { _ -> generatePassword() }


        return builder.create()
    }


    private fun generatePassword() {
        Logger.d()
        val lengthStr = mView.numberSizeText.text.toString()
        if (!NumberUtils.isDigits(lengthStr)) {
            Toast.makeText(context,
                    ContextUtils.formatString(R.string.error_required, R.string.numberSize),
                    Toast.LENGTH_SHORT)
                    .show()
            return
        }

        val length = lengthStr.toInt()
        if (length <= 0 || length > 99) {
            Toast.makeText(context,
                    R.string.error_numberSizeLength,
                    Toast.LENGTH_SHORT)
                    .show()
            return
        }

        if (!mView.smallAlphabetCheckBox.isChecked
                && !mView.bigAlphabetCheckBox.isChecked
                && !mView.numberLetterCheckBox.isChecked
                && !mView.symbolLetterCheckBox.isChecked) {
            Toast.makeText(context,
                    ContextUtils.formatString(R.string.error_noSelected, R.string.letterType),
                    Toast.LENGTH_SHORT)
                    .show()
            return
        }
        val password = PasswordStrings.generatePasswordStr(mView.numberSizeText.text.toString().toInt(),
                mView.smallAlphabetCheckBox.isChecked,
                mView.bigAlphabetCheckBox.isChecked,
                mView.numberLetterCheckBox.isChecked,
                mView.symbolLetterCheckBox.isChecked)

        Logger.d("generated passowrd:[%s]", password)

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