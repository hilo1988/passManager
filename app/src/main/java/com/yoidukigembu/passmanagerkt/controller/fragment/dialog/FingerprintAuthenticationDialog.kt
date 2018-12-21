package com.yoidukigembu.passmanagerkt.controller.fragment.dialog

import android.annotation.TargetApi
import android.app.DialogFragment
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.model.helper.FingerprintUiHelper
import com.yoidukigembu.passmanagerkt.model.holder.KeyStoreHolder
import kotlinx.android.synthetic.main.fingerprint_dialog_backup.*
import kotlinx.android.synthetic.main.fingerprint_dialog_container.*
import kotlinx.android.synthetic.main.fingerprint_dialog_content.*

/**
 * Created by hilo on 2016/11/06.
 */
@TargetApi(Build.VERSION_CODES.M)
class FingerprintAuthenticationDialog : DialogFragment(), TextView.OnEditorActionListener, FingerprintUiHelper.Callback {


    private var authCallback: ((FingerprintManager.CryptoObject?) -> Unit)? = null
    private var failedCallback: (() -> Unit)? = null
    private var defaultKey: String? = null


    private var mStage = Stage.FINGERPRINT

    private var mCryptoObject: FingerprintManager.CryptoObject? = null
    private var mFingerprintUiHelper: FingerprintUiHelper? = null

    private var mInputMethodManager: InputMethodManager? = null

    private val mShowKeyboardRunnable = Runnable { mInputMethodManager!!.showSoftInput(password, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Do not create a new Fragment when the Activity is re-created such as orientation changes.
        retainInstance = true
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog)
        mInputMethodManager = context.getSystemService(InputMethodManager::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        dialog.setTitle(getString(R.string.sign_in))


        return inflater.inflate(R.layout.fingerprint_dialog_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        password!!.setOnEditorActionListener(this)

        mFingerprintUiHelper = FingerprintUiHelper(
                context.getSystemService(FingerprintManager::class.java),
                fingerprint_icon!!,
                fingerprint_status!!, this)
        updateStage()

        // If fingerprint authentication is not available, switch immediately to the backup
        // (password) screen.
        if (!mFingerprintUiHelper!!.isFingerprintAuthAvailable) {
            goToBackup()
        }
    }

    override fun onResume() {
        super.onResume()
        if (mStage == Stage.FINGERPRINT) {
            mFingerprintUiHelper?.startListening(mCryptoObject)
        }
    }

    fun setStage(stage: Stage) {
        mStage = stage
    }

    override fun onPause() {
        super.onPause()
        mFingerprintUiHelper!!.stopListening()
    }


    /**
     * Sets the crypto object to be passed in when authenticating with fingerprint.
     */
    fun setCryptoObject(cryptoObject: FingerprintManager.CryptoObject) {
        mCryptoObject = cryptoObject
    }

    /**
     * Switches to backup (password) screen. This either can happen when fingerprint is not
     * available or the user chooses to use the password authentication method by pressing the
     * button. This can also happen when the user had too many fingerprint attempts.
     */
    private fun goToBackup() {
        mStage = Stage.PASSWORD
        updateStage()
        password!!.requestFocus()

        // Show the keyboard.
        password!!.postDelayed(mShowKeyboardRunnable, 500)

        // Fingerprint is not used anymore. Stop listening for it.
        mFingerprintUiHelper!!.stopListening()
    }

    /**
     * Checks whether the current entered password is correct, and dismisses the the dialog and
     * let's the activity know about the result.
     */
    private fun verifyPassword() {
        if (!checkPassword(password!!.text.toString())) {
            return
        }
        if (mStage == Stage.NEW_FINGERPRINT_ENROLLED) {
            //            SharedPreferences.Editor editor = mSharedPreferences.edit();
            //            editor.putBoolean(getString(R.string.use_fingerprint_to_authenticate_key),
            //                    use_fingerprint_in_future_check.isChecked());
            //            editor.apply();

            if (use_fingerprint_in_future_check!!.isChecked) {
                // Re-create the key so that fingerprints including new ones are validated.
                KeyStoreHolder.createKey(defaultKey!!, true)
                mStage = Stage.FINGERPRINT
            }
        }
        password!!.setText("")
        // TODO これはfailなのか・・・？
        failedCallback?.let { it() }
        dismiss()
    }

    /**
     * @return true if `password` is correct, false otherwise
     */
    private fun checkPassword(password: String): Boolean {
        // Assume the password is always correct.
        // In the real world situation, the password needs to be verified in the server side.
        return password.length > 0
    }

    private fun updateStage() {
        when (mStage) {
            FingerprintAuthenticationDialog.Stage.FINGERPRINT -> {
                cancel_button!!.setText(R.string.cancel)
                second_dialog_button!!.setText(R.string.use_password)
                fingerprint_container!!.visibility = View.VISIBLE
                backup_container!!.visibility = View.GONE
            }
            FingerprintAuthenticationDialog.Stage.NEW_FINGERPRINT_ENROLLED,
                // Intentional fall through
            FingerprintAuthenticationDialog.Stage.PASSWORD -> {
                cancel_button!!.setText(R.string.cancel)
                second_dialog_button!!.setText(R.string.ok)
                fingerprint_container!!.visibility = View.GONE
                backup_container!!.visibility = View.VISIBLE
                if (mStage == Stage.NEW_FINGERPRINT_ENROLLED) {
                    password_description!!.visibility = View.GONE
                    new_fingerprint_enrolled_description!!.visibility = View.VISIBLE
                    use_fingerprint_in_future_check!!.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            verifyPassword()
            return true
        }
        return false
    }

    override fun onAuthenticated() {
        // Callback from FingerprintUiHelper. Let the activity know that authentication was
        // successful.
        dismiss()
        authCallback?.let {
            it(mCryptoObject)
        }
    }

    override fun onError() {
        goToBackup()
    }


    fun onClickCancelButton(v: View) {
        dismiss()
    }


    fun onClickSecondDialogButton(v: View) {
        if (mStage == Stage.FINGERPRINT) {
            goToBackup()
        } else {
            verifyPassword()
        }
    }

    /**
     * Enumeration to indicate which authentication method the user is trying to authenticate with.
     */
    enum class Stage {
        FINGERPRINT,
        NEW_FINGERPRINT_ENROLLED,
        PASSWORD
    }


    companion object {


        fun newInstance(
                defaultKey: String,
                authenticationCallback: ((FingerprintManager.CryptoObject?) -> Unit),
                failedCallback: (() -> Unit)): FingerprintAuthenticationDialog {

            val dialog = FingerprintAuthenticationDialog()
            dialog.authCallback = authenticationCallback
            dialog.failedCallback = failedCallback
            dialog.defaultKey = defaultKey
            return dialog
        }
    }


}
