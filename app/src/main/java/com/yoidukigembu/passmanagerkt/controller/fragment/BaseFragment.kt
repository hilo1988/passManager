package com.yoidukigembu.passmanagerkt.controller.fragment

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.yoidukigembu.passmanagerkt.util.Logger

/**
 * 基底フラグメント
 */
open class BaseFragment : Fragment() {

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        Logger.v()
    }

    override fun onResume() {
        super.onResume()
        Logger.v()
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        Logger.v()
        return super.onContextItemSelected(item)
    }

    override fun getView(): View {
        Logger.v()
        return super.getView()
    }

    override fun onDetach() {
        super.onDetach()
        Logger.v()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Logger.v()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Logger.v()
    }


    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        Logger.v("startActivity Intent:%s", intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Logger.v()
    }

    override fun onStop() {
        super.onStop()
        Logger.v()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.v()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Logger.v()
    }


    override fun onStart() {
        super.onStart()
        Logger.v()
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        Logger.v()
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        super.onOptionsMenuClosed(menu)
        Logger.v()
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.v()
    }
}