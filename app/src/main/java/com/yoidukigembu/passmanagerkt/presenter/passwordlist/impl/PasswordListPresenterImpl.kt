package com.yoidukigembu.passmanagerkt.presenter.passwordlist.impl

import android.content.DialogInterface
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.controller.fragment.dialog.MessageDialogFragment
import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.db.entity.Password_Relation
import com.yoidukigembu.passmanagerkt.enums.PasswordMenu
import com.yoidukigembu.passmanagerkt.model.holder.RepositoryHolder
import com.yoidukigembu.passmanagerkt.presenter.passwordlist.PasswordListPresenter
import com.yoidukigembu.passmanagerkt.util.ContextUtils
import com.yoidukigembu.passmanagerkt.util.Logger
import com.yoidukigembu.passmanagerkt.valueobject.Cryptor
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PasswordListPresenterImpl(val processor: PasswordListPresenter.FragmentProcessor) : PasswordListPresenter {


    override fun selectPasswordList() {

        Single.create(SingleOnSubscribe<Password_Relation> { e -> e.onSuccess(RepositoryHolder.passwordRepository.getListCursor()) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { relation -> processor.showPasswordList(relation) }

    }

    override fun onListItemClicked(id: Long) {
        Logger.d("id: %d", id)
        Single
                .create(SingleOnSubscribe<Password> { e ->
                    val password = RepositoryHolder.passwordRepository.findById(id)

                    e.onSuccess(password!!)

                })
//                .doOnError { Toast.makeText(processor.getContext(), "パスワードが見つかりません。", Toast.LENGTH_SHORT).show() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { entity -> processor.showPasswordMenu(entity, PasswordMenu.createMenuDataList(entity)) }
    }

    override fun onMenuSelected(id: Long, password: Password) {
        val menu = PasswordMenu.get(id)
        when {
            menu == PasswordMenu.SHOW_DETAIL -> processor.showDetail(password)
            menu == PasswordMenu.COPY_LOGIN_ID && password.loginId != null -> copyLoginId(password.loginId!!)
            menu == PasswordMenu.COPY_PASSWORD1 -> copyPassword(Cryptor.getInstance().decrypt(password.password1!!))
            menu == PasswordMenu.EDIT -> processor.showEdit(id)
            menu == PasswordMenu.DELETE -> showDeleteDialog(password)
        }
    }


    /**
     * ログインIDのコピー
     */
    private fun copyLoginId(str: String?) {
        ContextUtils.copyToClipBoard(str)
        processor.showToast(ContextUtils.formatString(R.string.format_copy,
                R.string.loginId))
    }


    /**
     * パスワードコピー
     */
    private fun copyPassword(str: String?) {
        ContextUtils.copyToClipBoard(str)

        processor.showToast(ContextUtils.formatString(R.string.format_copy,
                R.string.password))
    }

    /**
     * 削除ダイアログの表示
     */
    private fun showDeleteDialog(entity: Password) {
        val format = processor.getContext().getString(R.string.format_confirmDelete)
        val data = MessageDialogFragment.Companion.MessageData(format.format(entity.name ?: ""))
        data.positiveListener = DialogInterface.OnClickListener { dialog, which ->
            delete(entity)
            dialog.dismiss()
            processor.showToast(ContextUtils.formatString(R.string.format_deleted, R.string.password))
        }

        val messageDialog = MessageDialogFragment.newInstance(data)
        processor.showDialog(messageDialog, "deleteDialog")

    }

    /**
     * パスワード削除
     */
    private fun delete(entity: Password) {
        Observable.create(ObservableOnSubscribe<Int> { sub ->
            val result = RepositoryHolder.passwordRepository
                    .deleteById(entity.id)
            Logger.i("パスワードを削除しました。rows:[%d]", result)
            sub.onNext(result)
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    processor.showToast(ContextUtils.formatString(R.string.format_deleted, R.string.password))
                    processor.notifyDataSetChanged()
                }

    }


}