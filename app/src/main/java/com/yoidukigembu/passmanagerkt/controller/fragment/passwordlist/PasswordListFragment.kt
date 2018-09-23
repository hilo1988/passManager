package com.yoidukigembu.passmanagerkt.controller.fragment.passwordlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.BaseFragment
import com.yoidukigembu.passmanagerkt.controller.fragment.dialog.ListMenuDialogFragment
import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.db.entity.Password_Relation
import com.yoidukigembu.passmanagerkt.functionalinterface.controller.fragment.dialog.OnMenuSelectedListener
import com.yoidukigembu.passmanagerkt.presenter.passwordlist.PasswordListPresenter
import com.yoidukigembu.passmanagerkt.presenter.passwordlist.impl.PasswordListPresenterImpl
import com.yoidukigembu.passmanagerkt.valueobject.MenuData
import com.yoidukigembu.passmanagerkt.view.adapter.PasswordAdapter
import kotlinx.android.synthetic.main.fragment_password_list.*

/**
 * パスワード一覧フラグメント
 */
class PasswordListFragment : BaseFragment(), PasswordListPresenter.FragmentProcessor {

    lateinit var operator: ActivityOperator

    /** プレゼンタ */
    lateinit var presenter: PasswordListPresenter

    /** アダプタ */
    lateinit var adapter: PasswordAdapter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_password_list, null)
        return view!!
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        passwordListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, id ->
            presenter.onListItemClicked(id)
        }

        addButton.setOnClickListener(View.OnClickListener { _ -> operator.showAddFragment() })
    }


    override fun showPasswordList(relation: Password_Relation) {
        adapter = PasswordAdapter(context, relation)
    }

    override fun showPasswordMenu(password: Password, menuList: List<MenuData>) {

        val dialog = ListMenuDialogFragment.newInstance(menuList,
                OnMenuSelectedListener { id -> presenter.onMenuSelected(id, password) })
        dialog.show(fragmentManager, ListMenuDialogFragment::javaClass.name)
    }

    override fun showDetail(password: Password) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEdit(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun notifyDataSetChanged() {
        passwordListView.deferNotifyDataSetChanged()
    }

    companion object {
        fun newInstance(operator: ActivityOperator): PasswordListFragment {
            val f = PasswordListFragment()
            val presenter = PasswordListPresenterImpl(f)
            f.presenter = presenter
            f.operator = operator
            return f
        }
    }

    /**
     * アクティビティでの処理
     */
    interface ActivityOperator : BaseActivityOperator {

        /**
         * 追加フラグメントの表示
         */
        fun showAddFragment()
    }


}