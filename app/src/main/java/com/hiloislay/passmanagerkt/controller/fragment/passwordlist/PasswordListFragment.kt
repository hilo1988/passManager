package com.hiloislay.passmanagerkt.controller.fragment.passwordlist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.hiloislay.passmanagerkt.R
import com.hiloislay.passmanagerkt.controller.fragment.BaseFragment
import com.hiloislay.passmanagerkt.controller.fragment.dialog.ListMenuDialogFragment
import com.hiloislay.passmanagerkt.databinding.FragmentPasswordListBinding
import com.hiloislay.passmanagerkt.db.realm.entity.Password
import com.hiloislay.passmanagerkt.model.holder.SubjectHolder
import com.hiloislay.passmanagerkt.presenter.passwordlist.PasswordListPresenter
import com.hiloislay.passmanagerkt.presenter.passwordlist.impl.PasswordListPresenterImpl
import com.hiloislay.passmanagerkt.util.ContextUtils
import com.hiloislay.passmanagerkt.valueobject.MenuData
import com.hiloislay.passmanagerkt.view.adapter.PasswordAdapter
import com.hiloislay.passmanagerkt.view.adapter.PasswordRecyclerAdapter
import com.hiloislay.passmanagerkt.viewmodel.PasswordListViewModel
import io.realm.RealmList
import kotlinx.android.synthetic.main.fragment_password_list.*

/**
 * パスワード一覧フラグメント
 */
class PasswordListFragment : BaseFragment() {

    lateinit var operator: ActivityOperator

    lateinit var binding:FragmentPasswordListBinding

    /** アダプタ */
    private var adapter: PasswordAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password_list, null, false)
        val adapter = PasswordRecyclerAdapter(context!!, RealmList())
        binding.viewModel = PasswordListViewModel(this, adapter)
        adapter.onClickListener = {ContextUtils.copyToClipBoard(it?.name)}
        PasswordRecyclerAdapter(context!!, RealmList()).let {
            binding.viewModel = PasswordListViewModel(this, it)
            it.onClickListener =
        }

        return binding.root
    }



    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.selectPasswordList()

        passwordListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, id ->
            presenter.copyPassword(id)
        }

        passwordListView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, _, id ->
            presenter.onListItemClicked(id)
            true
        }

        addButton.setOnClickListener { operator.showAddFragment() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


    override fun showPasswordList(results: List<Password>) {
        val adapter = PasswordAdapter(context, results)
        passwordListView.adapter = adapter
        this.adapter = adapter

        SubjectHolder.onPasswordChangedSubject.subscribe {
            passwordListView.deferNotifyDataSetChanged()
        }
    }

    override fun showPasswordMenu(password: Password, menuList: List<MenuData>) {

        val dialog = ListMenuDialogFragment.newInstance(menuList) { id -> presenter.onMenuSelected(id, password) }
        dialog.show(fragmentManager, ListMenuDialogFragment::javaClass.name)
    }

    override fun showDetail(password: Password) {
        operator.showDetailFragment(password)
    }

    override fun showEdit(id: Long) {
        operator.showEditFragment(id)
    }

    override fun openUrl(url: String) {
        operator.openUrl(url)
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

        /**
         * 変更フラグメントの表示
         */
        fun showEditFragment(id: Long)

        /**
         * URLの表示
         */
        fun openUrl(url: String)

        /**
         * 詳細フラグメントの表示
         */
        fun showDetailFragment(entity: Password)
    }

    class Handler {

    }


}