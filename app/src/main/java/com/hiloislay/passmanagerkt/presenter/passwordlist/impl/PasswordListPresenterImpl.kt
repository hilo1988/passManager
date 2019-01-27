package com.hiloislay.passmanagerkt.presenter.passwordlist.impl

import android.content.DialogInterface
import com.hiloislay.passmanagerkt.controller.fragment.dialog.MessageDialogFragment
import com.hiloislay.passmanagerkt.db.realm.entity.Password
import com.hiloislay.passmanagerkt.enums.PasswordMenu
import com.hiloislay.passmanagerkt.model.holder.RepositoryHolder
import com.hiloislay.passmanagerkt.presenter.passwordlist.PasswordListPresenter
import com.hiloislay.passmanagerkt.util.ContextUtils
import com.hiloislay.passmanagerkt.util.Logger
import com.hiloislay.passmanagerkt.valueobject.Cryptor
import io.realm.RealmResults

class PasswordListPresenterImpl(private val processor: PasswordListPresenter.FragmentProcessor) : PasswordListPresenter {

    private var passwordList: RealmResults<Password>? = null

    override fun onDestroy() {
        super.onDestroy()
        passwordList?.removeAllChangeListeners()
    }

    override fun selectPasswordList() {

        passwordList = RepositoryHolder.passwordRepository.selectList()
        passwordList?.addChangeListener { a -> Logger.w("passwordListChanged"); processor.showPasswordList(a.toList()) }
        processor.showPasswordList(passwordList!!.toList())

    }

    override fun onListItemClicked(id: Long) {
        com.hiloislay.passmanagerkt.util.Logger.d("id: %d", id)


        RepositoryHolder.passwordRepository
                .findById(id)
                ?.let { processor.showPasswordMenu(it, PasswordMenu.createMenuDataList(it)) }
    }

    override fun onMenuSelected(id: Long, password: Password) {
        val menu = PasswordMenu.get(id)
        when (menu) {
            PasswordMenu.SHOW_DETAIL -> processor.showDetail(password)
            PasswordMenu.COPY_LOGIN_ID -> password.loginId?.apply { copyLoginId(this) }
            PasswordMenu.COPY_PASSWORD1 -> password.password1?.apply { copyPassword(Cryptor.getInstance().decrypt(this)) }
            PasswordMenu.COPY_PASSWORD2 -> password.password2?.apply { copyPassword(Cryptor.getInstance().decrypt(this)) }
            PasswordMenu.EDIT -> processor.showEdit(password.id)
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
        processor.showToast(ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.format_copy,
                com.hiloislay.passmanagerkt.R.string.loginId))
    }


    /**
     * パスワードコピー
     */
    private fun copyPassword(str: String?) {
        ContextUtils.copyToClipBoard(str)

        processor.showToast(ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.format_copy,
                com.hiloislay.passmanagerkt.R.string.password))
    }

    /**
     * 削除ダイアログの表示
     */
    private fun showDeleteDialog(entity: Password) {
        val format = processor.getContext().getString(com.hiloislay.passmanagerkt.R.string.format_confirmDelete)
        val data = MessageDialogFragment.Companion.MessageData(format.format(entity.name ?: ""))
        data.positiveListener = DialogInterface.OnClickListener { dialog, which ->
            delete(entity)
            dialog.dismiss()
            processor.showToast(ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.format_deleted, com.hiloislay.passmanagerkt.R.string.password))
        }

        data.negativeTitle = processor.getContext().getString(com.hiloislay.passmanagerkt.R.string.cancel)

        val messageDialog = MessageDialogFragment.newInstance(data)
        processor.showDialog(messageDialog, "deleteDialog")

    }

    /**
     * パスワード削除
     */
    private fun delete(entity: Password) {
        RepositoryHolder.passwordRepository
                .deleteById(entity.id)
                .let {
                    processor.showToast(ContextUtils.formatString(com.hiloislay.passmanagerkt.R.string.format_deleted, com.hiloislay.passmanagerkt.R.string.password))
                    com.hiloislay.passmanagerkt.util.Logger.i("パスワードを削除しました。rows:[%d]", it)
                }


    }

    private fun share(entity: Password) {
        val sb = StringBuilder()
                .append(entity.name)
                .append("の情報\n")
    }


}