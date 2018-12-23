package com.yoidukigembu.passmanagerkt.presenter.passwordlist

import com.yoidukigembu.passmanagerkt.db.realm.entity.Password
import com.yoidukigembu.passmanagerkt.presenter.BasePresenter
import com.yoidukigembu.passmanagerkt.valueobject.MenuData

/**
 * パスワード一覧プレゼンタ
 */
interface PasswordListPresenter : BasePresenter {


    /**
     * パスワードの生成
     */
    fun selectPasswordList()


    /**
     * リストをクリックした時の処理
     */
    fun onListItemClicked(id: Long)

    /**
     * パスワードメニューを選択した時の処理
     */
    fun onMenuSelected(id: Long, password: Password)

    /**
     * フラグメント処理のI/F
     */
    interface FragmentProcessor : BasePresenter.BaseFragmentProcessor {

        /**
         * アダプタの作成
         */
        fun showPasswordList(results: List<Password>)

        /**
         * パスワードメニューの作成
         */
        fun showPasswordMenu(password: Password, menuList: List<MenuData>)

        /**
         * 詳細表示
         */
        fun showDetail(password: Password)

        /**
         * 変更画面表示
         */
        fun showEdit(id: Long)

        /**
         * URLを開く
         */
        fun openUrl(url: String)

    }
}