package com.hiloislay.passmanagerkt.controller.fragment

import android.app.DialogFragment
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.hiloislay.passmanagerkt.presenter.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * 基底フラグメント
 */
abstract class BaseFragment : Fragment(), BasePresenter.BaseFragmentProcessor {

    var cDisposable: CompositeDisposable? = null

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        Timber.v("fragment:%s", this)
    }

    override fun onResume() {
        super.onResume()
        Timber.v("fragment:%s", this)
    }


    override fun onDetach() {
        super.onDetach()
        Timber.v("fragment:%s", this)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Timber.v("fragment:%s", this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Timber.v("fragment:%s", this)
    }


    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        Timber.v("fragment:%s", this)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Timber.v("fragment:%s", this)
    }

    override fun onStop() {
        super.onStop()
        Timber.v("fragment:%s", this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.cDisposable = CompositeDisposable()
        Timber.v("fragment:%s", this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.v("fragment:%s", this)
    }


    override fun onStart() {
        super.onStart()
        Timber.v("fragment:%s", this)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        Timber.v("fragment:%s", this)
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        super.onOptionsMenuClosed(menu)
        Timber.v("fragment:%s", this)
    }

    override fun onDestroy() {
        super.onDestroy()
        cDisposable?.dispose()
        Timber.v("fragment:%s", this)
    }

    override fun showDialog(dialogFragment: DialogFragment, tag: String) {
        dialogFragment.show(fragmentManager, tag)
    }

    override fun showToast(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(stringRes: Int) {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show()
    }

    override fun getDisposable(): CompositeDisposable? = this.cDisposable

    /**
     * アクティビティに処理させる基底I/F
     */
    interface BaseActivityOperator
}