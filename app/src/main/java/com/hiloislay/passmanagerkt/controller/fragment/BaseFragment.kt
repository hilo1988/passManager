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
import com.hiloislay.passmanagerkt.util.Logger
import io.reactivex.disposables.CompositeDisposable

/**
 * 基底フラグメント
 */
open class BaseFragment : Fragment(), BasePresenter.BaseFragmentProcessor {

    var cDisposable: CompositeDisposable? = null

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        Logger.v()
    }

    override fun onResume() {
        super.onResume()
        Logger.v("self:[%s]", this)
    }


    override fun onDetach() {
        super.onDetach()
        Logger.v("self:[%s]", this)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Logger.v("self:[%s]", this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Logger.v("self:[%s]", this)
    }


    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        Logger.v("self: %s Intent:%s", this, intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Logger.v("self:[%s]", this)
    }

    override fun onStop() {
        super.onStop()
        Logger.v("self:[%s]", this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.cDisposable = CompositeDisposable()
        Logger.v("self:[%s]", this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Logger.v("self:[%s]", this)
    }


    override fun onStart() {
        super.onStart()
        Logger.v("self:[%s]", this)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        Logger.v("self:[%s]", this)
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        super.onOptionsMenuClosed(menu)
        Logger.v("self:[%s]", this)
    }

    override fun onDestroy() {
        super.onDestroy()
        cDisposable?.dispose()
        Logger.v("self:[%s]", this)
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