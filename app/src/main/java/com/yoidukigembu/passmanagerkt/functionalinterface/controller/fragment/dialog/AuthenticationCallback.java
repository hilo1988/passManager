package com.yoidukigembu.passmanagerkt.functionalinterface.controller.fragment.dialog;

import android.hardware.fingerprint.FingerprintManager;


/**
 * 指紋認証に成功した時のコールバック
 */
public interface AuthenticationCallback {

    void onAuthenticated(FingerprintManager.CryptoObject cryptoObject);
}
