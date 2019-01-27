package com.hiloislay.passmanagerkt.model.helper

/**
 * Created by hilo on 2016/10/27.
 */

import android.annotation.TargetApi
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import android.widget.ImageView
import android.widget.TextView
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.util.Logger

/**
 * Small helper class to manage text/icon around fingerprint authentication UI.
 */
@TargetApi(Build.VERSION_CODES.M)
class FingerprintUiHelper
/**
 * Constructor for [FingerprintUiHelper].
 */
(private val mFingerprintManager: FingerprintManager,
 private val mIcon: ImageView, private val mErrorTextView: TextView, private val mCallback: Callback) : FingerprintManager.AuthenticationCallback() {
    private var mCancellationSignal: CancellationSignal? = null

    private var mSelfCancelled: Boolean = false

    // The line below prevents the false positive inspection from Android Studio
    val isFingerprintAuthAvailable: Boolean
        get() = mFingerprintManager.isHardwareDetected && mFingerprintManager.hasEnrolledFingerprints()

    private val mResetErrorTextRunnable = Runnable {
        mErrorTextView.setTextColor(
                mErrorTextView.resources.getColor(com.hiloislay.passmanagerkt.R.color.hint_color, null))
        mErrorTextView.text = mErrorTextView.resources.getString(com.hiloislay.passmanagerkt.R.string.fingerprint_hint)
        mIcon.setImageResource(com.hiloislay.passmanagerkt.R.mipmap.ic_fp_40px)
    }

    fun startListening(cryptoObject: FingerprintManager.CryptoObject?) {
        if (!isFingerprintAuthAvailable) {
            return
        }
        mCancellationSignal = CancellationSignal()
        // The line below prevents the false positive inspection from Android Studio

        mFingerprintManager
                .authenticate(cryptoObject, mCancellationSignal, 0 /* flags */, this, null)
        mIcon.setImageResource(com.hiloislay.passmanagerkt.R.mipmap.ic_fp_40px)
    }

    fun stopListening() {
        if (mCancellationSignal != null) {
            mSelfCancelled = true
            mCancellationSignal!!.cancel()
            mCancellationSignal = null
        }
    }

    override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
        com.hiloislay.passmanagerkt.util.Logger.e("errorMsgId:[%d] msg:[%s]", errMsgId, errString)
        if (!mSelfCancelled) {
            showError(errString)
            mIcon.postDelayed({ mCallback.onError() },
                    ERROR_TIMEOUT_MILLIS)
        }
    }

    override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
        com.hiloislay.passmanagerkt.util.Logger.v("helpMsgId:[%d] helpString:[%s]", helpMsgId, helpString)
        showError(helpString)
    }

    override fun onAuthenticationFailed() {
        com.hiloislay.passmanagerkt.util.Logger.e()
        showError(mIcon.resources.getString(
                com.hiloislay.passmanagerkt.R.string.fingerprint_not_recognized))
    }

    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
        com.hiloislay.passmanagerkt.util.Logger.v("result:[%s]", result)
        mErrorTextView.removeCallbacks(mResetErrorTextRunnable)
        mIcon.setImageResource(com.hiloislay.passmanagerkt.R.drawable.ic_fingerprint_success)
        mErrorTextView.setTextColor(
                mErrorTextView.resources.getColor(com.hiloislay.passmanagerkt.R.color.success_color, null))
        mErrorTextView.text = mErrorTextView.resources.getString(com.hiloislay.passmanagerkt.R.string.fingerprint_success)
        mIcon.postDelayed({ mCallback.onAuthenticated() }, SUCCESS_DELAY_MILLIS)
    }

    private fun showError(error: CharSequence) {
        mIcon.setImageResource(com.hiloislay.passmanagerkt.R.drawable.ic_fingerprint_error)
        mErrorTextView.text = error
        mErrorTextView.setTextColor(
                mErrorTextView.resources.getColor(com.hiloislay.passmanagerkt.R.color.warning_color, null))
        mErrorTextView.removeCallbacks(mResetErrorTextRunnable)
        mErrorTextView.postDelayed(mResetErrorTextRunnable, ERROR_TIMEOUT_MILLIS)
    }

    interface Callback {

        fun onAuthenticated()

        fun onError()
    }

    companion object {

        private val ERROR_TIMEOUT_MILLIS: Long = 1600
        private val SUCCESS_DELAY_MILLIS: Long = 1300
    }
}