package com.yoidukigembu.passmanagerkt.db.repository.impl

import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.db.entity.Password_Relation
import com.yoidukigembu.passmanagerkt.db.repository.PasswordRepository

class PasswordRepositoryImpl : BaseRepositoryImpl<Password>(), PasswordRepository {

    override fun findById(id: Long): Password? {
        return database.selectFromPassword()
                .idEq(id)
                .firstOrNull()
    }

    override fun getListCursor(): Password_Relation {
        return database.relationOfPassword()
                .orderByIdAsc()

    }

    override fun deleteById(id: Long): Int {
        return database.deleteFromPassword()
                .idEq(id)
                .execute()
    }

    override fun insert(entity: Password): Long {
        return database.insertIntoPassword(entity)
    }

    override fun update(entity: Password): Int {
        return database.updatePassword()
                .idEq(entity.id)
                .loginId(entity.loginId)
                .name(entity.name)
                .password1(entity.password1)
                .password2(entity.password2)
                .loginUrl(entity.loginUrl)
                .memo(entity.memo)
                .execute()
    }

    override fun updateOrderByKey(id: Long, orderByKey: Int): Int {
        return database.updatePassword()
                .idEq(id)
                .orderByKey(orderByKey)
                .execute()
    }

    override fun selectMaxOrderByKey(): Int {
        return database.selectFromPassword()
                .maxByOrderByKey() ?: 0
    }
}