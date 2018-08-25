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

}