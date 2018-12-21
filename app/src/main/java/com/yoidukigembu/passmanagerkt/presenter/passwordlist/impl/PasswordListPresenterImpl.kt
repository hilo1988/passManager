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
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PasswordListPresenterImpl(private val processor: PasswordListPresenter.FragmentProcessor) : PasswordListPresenter {


    override fun selectPasswordList() {


        Single.create(SingleOnSubscribe<Password_Relation> { e -> e.onSuccess(RepositoryHolder.passwordRepository.getListCursor()) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { relation -> processor.showPasswordList(relation) }

    }

    override fun onListItemClicked(id: Long) {
        Logger.d("id: %d", id)


        RepositoryHolder.passwordRepository
                .findById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { processor.showPasswordMenu(it, PasswordMenu.createMenuDataList(it)) },
                        { Logger.e(it, "パスワードセレクト時にエラー") })
                .let { processor.getDisposable()?.add(it) }
    }

    override fun onMenuSelected(id: Long, password: Password) {
        val menu = PasswordMenu.get(id)
        when (menu) {
            PasswordMenu.SHOW_DETAIL -> processor.showDetail(password)
            PasswordMenu.COPY_LOGIN_ID -> password.loginId?.apply { copyLoginId(this) }
            PasswordMenu.COPY_PASSWORD1 -> password.password1?.apply { copyPassword(Cryptor.getInstance().decrypt(this)) }
            PasswordMenu.COPY_PASSWORD2 -> password.password2?.apply { copyPassword(Cryptor.getInstance().decrypt(this)) }
            PasswordMenu.EDIT -> processor.showEdit(id)
            PasswordMenu.DELETE -> showDeleteDialog(password)
            PasswordMenu.OPEN_LOGIN_URL -> password.loginUrl?.apply { processor.openUrl(this) }
            PasswordMenu.SEND_MAIL -> share(password)
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
        RepositoryHolder.passwordRepository
                .deleteById(entity.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    processor.showToast(ContextUtils.formatString(R.string.format_deleted, R.string.password))
                    processor.notifyDataSetChanged()
                    Logger.i("パスワードを削除しました。rows:[%d]", it)
                },
                        { Logger.w(it, "パスワードの削除に失敗しました") })
                .let { processor.getDisposable()?.add(it) }


    }

    private fun share(entity: Password) {
        val sb = StringBuilder()
                .append(entity.name)
                .append("の情報\n")
    }


}