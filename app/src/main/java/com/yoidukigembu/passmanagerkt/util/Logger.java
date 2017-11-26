package com.yoidukigembu.passmanagerkt.util;

import android.util.Log;
import android.widget.Toast;
import com.yoidukigembu.passmanagerkt.PMApplication;

import java.util.Locale;

/**
 * ロガー
 */
public class Logger {

    private static boolean mIsShowLog = true;

    private static int showLevelInfo = LogLevel.VERBOSE;

    public static void setShowLog(boolean isShowLog) {
        mIsShowLog = isShowLog;
    }

    private static final Locale currentLocale = Locale.JAPAN;


    public static void toast(String message, Object... args) {
        final String msg = String.format(currentLocale, message, args);
        outputLog(LogLevel.VERBOSE, null, msg);
        Toast.makeText(PMApplication.Companion.getContext(),
                msg,
                Toast.LENGTH_SHORT).show();
    }

    public static void toast(Throwable throwable, String message, Object... args) {
        final String msg = String.format(currentLocale, message, args);
        outputLog(LogLevel.VERBOSE, throwable, msg);
        Toast.makeText(PMApplication.Companion.getContext(),
                msg,
                Toast.LENGTH_SHORT).show();
    }

    public static void v() {
        outputLog(LogLevel.VERBOSE, null, "");
    }

    public static void v(String message, Object... args) {
        outputLog(LogLevel.VERBOSE,
                null,
                message,
                args);
    }

    public static void v(Throwable throwable, String message, Object... args) {
        outputLog(LogLevel.VERBOSE,
                throwable,
                message,
                args);
    }

    public static void d() {
        outputLog(LogLevel.DEBUG, null, "");
    }

    public static void d(String message, Object... args) {
        outputLog(LogLevel.DEBUG,
                null,
                message,
                args);
    }

    public static void d(Throwable throwable, String message, Object... args) {
        outputLog(LogLevel.DEBUG,
                throwable,
                message,
                args);
    }


    public static void i() {
        outputLog(LogLevel.INFO, null, "");
    }

    public static void i(String message, Object... args) {
        outputLog(LogLevel.INFO,
                null,
                message,
                args);
    }

    public static void i(Throwable throwable, String message, Object... args) {
        outputLog(LogLevel.INFO,
                throwable,
                message,
                args);
    }

    public static void w() {
        outputLog(LogLevel.WARN, null, "");
    }

    public static void w(String message, Object... args) {
        outputLog(LogLevel.WARN,
                null,
                message,
                args);
    }

    public static void w(Throwable throwable, String message, Object... args) {
        outputLog(LogLevel.WARN,
                throwable,
                message,
                args);
    }


    public static void e() {
        outputLog(LogLevel.ERROR, null, "");
    }

    public static void e(String message, Object... args) {
        outputLog(LogLevel.ERROR,
                null,
                message,
                args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        outputLog(LogLevel.ERROR,
                throwable,
                message,
                args);
    }


    public static void wtf() {
        outputLog(LogLevel.WTF, null, "");
    }

    public static void wtf(String message, Object... args) {
        outputLog(LogLevel.WTF,
                null,
                message,
                args);
    }

    public static void wtf(Throwable throwable, String message, Object... args) {
        outputLog(LogLevel.WTF,
                throwable,
                message,
                args);
    }


    // **********************************************************************
    // プライベートメソッド
    // **********************************************************************

    private static void outputLog(int type, Throwable throwable, String message, Object... args) {
        if (!mIsShowLog) {
            // ログ出力フラグが立っていない場合は何もしません。
            return;
        }

        if (type < showLevelInfo) {
            // 表示レベルを満たさなければ何もしない
            return;
        }

        StackTraceElement elm = getStackTraceInfo();
        String TAG = elm.getClassName();
        String msg = String.format(Locale.JAPAN, "%s @%d %s",
                elm.getMethodName(),
                elm.getLineNumber(),
                String.format(currentLocale, message, args)
        );

        // ログを出力!
        switch (type) {
            case LogLevel.VERBOSE:
                if (throwable == null) {
                    Log.v(TAG, msg);
                } else {
                    Log.v(TAG, msg, throwable);
                }
                break;
            case LogLevel.DEBUG:
                if (throwable == null) {
                    Log.d(TAG, msg);
                } else {
                    Log.d(TAG, msg, throwable);
                }
                break;

            case LogLevel.INFO:
                if (throwable == null) {
                    Log.i(TAG, msg);
                } else {
                    Log.i(TAG, msg, throwable);
                }
                break;

            case LogLevel.WARN:
                if (throwable == null) {
                    Log.w(TAG, msg);
                } else {
                    Log.w(TAG, msg, throwable);
                }
                break;

            case LogLevel.ERROR:
                if (throwable == null) {
                    Log.e(TAG, msg);
                } else {
                    Log.e(TAG, msg, throwable);
                }
                break;

            case LogLevel.WTF:
                if (throwable == null) {
                    Log.wtf(TAG, msg);
                } else {
                    Log.wtf(TAG, msg, throwable);
                }
                break;
        }


    }

    /**
     * スタックトレースから呼び出し元の基本情報を取得。
     *
     * @return <<className#methodName:lineNumber>>
     */
    private static StackTraceElement getStackTraceInfo() {
        // 現在のスタックトレースを取得。
        // 0:VM 1:スレッド 2:getStackTraceInfo() 3:outputLog() 4:logDebug()等 5:呼び出し元
        StackTraceElement element = Thread.currentThread().getStackTrace()[5];

        return element;
    }

    private static class LogLevel {
        private static final int VERBOSE = 1;
        private static final int DEBUG = 2;
        private static final int INFO = 3;
        private static final int WARN = 4;
        private static final int ERROR = 5;
        private static final int WTF = 6;
    }
}
