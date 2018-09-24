package com.yoidukigembu.passmanagerkt.enums

import com.yoidukigembu.passmanagerkt.PMApplication
import com.yoidukigembu.passmanagerkt.R
import com.yoidukigembu.passmanagerkt.accessor.MenuDataAccessor
import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.valueobject.MenuData

enum class PasswordMenu(val id: Long, val stringResId: Int) : MenuDataAccessor {

    SHOW_DETAIL(1, R.string.showDetail),
    SEND_MAIL(2, R.string.sendMail),
    OPEN_LOGIN_URL(3, R.string.openLoginUrl),
    COPY_LOGIN_ID(4, R.string.copyLoginId),
    COPY_PASSWORD1(5, R.string.copyPassword1),
    COPY_PASSWORD2(6, R.string.copyPassword2),
    EDIT(7, R.string.edit),
    DELETE(8, R.string.delete);

    fun getString() = PMApplication.getContext().getString(stringResId)

    override fun getMenuId(): Long = id

    override fun getMenuName() = getString()

    companion object {
        fun createMenuDataList(password: Password): List<MenuData> {
            val list = mutableListOf(
                    MenuData(SHOW_DETAIL),
                    MenuData(SEND_MAIL)
            )

            if (password.loginUrl != null) {
                list.add(MenuData(OPEN_LOGIN_URL))
            }

            if (password.loginId != null) {
                list.add(MenuData(COPY_LOGIN_ID))
            }

            list.add(MenuData(COPY_PASSWORD1))

            if (password.password2 != null) {
                list.add(MenuData(COPY_PASSWORD2))
            }

            list.add(MenuData(COPY_PASSWORD2))
            list.add(MenuData(EDIT))
            list.add(MenuData(DELETE))

            return list
        }

        fun get(id: Long): PasswordMenu {
            return values().filter { menu -> menu.id == id }
                    .first()
        }
    }
}