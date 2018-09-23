package com.yoidukigembu.passmanagerkt.presenter.passwordlist

import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.db.entity.Password_Relation
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
        fun showPasswordList(relation: Password_Relation)

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
         * リスト更新
         */
        fun notifyDataSetChanged()

    }
}