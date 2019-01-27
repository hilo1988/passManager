package com.hiloislay.passmanagerkt.valueobject

import android.widget.TextView
import com.hiloislay.passmanagerkt.PMApplication
import com.hiloislay.passmanagerkt.R

class SecretlyTextData(private val textView: TextView,
                       var text: CharSequence?) {

    var isHidden = true


    init {
        textView.text = PMApplication.getContext().getString(com.hiloislay.passmanagerkt.R.string.hiddenMarks)
    }


    fun toggleHidden(): Boolean {
        textView.text = if (isHidden) text
        else PMApplication.getContext().getString(com.hiloislay.passmanagerkt.R.string.hiddenMarks)

        isHidden = !isHidden
        return isHidden
    }


}