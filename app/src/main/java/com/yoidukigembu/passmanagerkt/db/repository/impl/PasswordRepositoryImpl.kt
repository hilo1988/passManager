package com.yoidukigembu.passmanagerkt.db.repository.impl

import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.db.entity.Password_Relation
import com.yoidukigembu.passmanagerkt.db.repository.PasswordRepository
import io.reactivex.Single

class PasswordRepositoryImpl : BaseRepositoryImpl<Password>(), PasswordRepository {

    override fun findById(id: Long) =
            database.selectFromPassword()
                    .idEq(id)
                    .executeAsObservable()

    override fun getListCursor(): Password_Relation {
        return database.relationOfPassword()
                .orderByIdAsc()

    }

    override fun deleteById(id: Long) = database.deleteFromPassword()
            .idEq(id)
            .executeAsSingle()

    override fun insert(entity: Password) = database.prepareInsertIntoPassword()
            .executeAsSingle(entity)

    override fun update(entity: Password): Single<Int> {
        return database.updatePassword()
                .idEq(entity.id)
                .loginId(entity.loginId)
                .name(entity.name)
                .password1(entity.password1)
                .password2(entity.password2)
                .loginUrl(entity.loginUrl)
                .memo(entity.memo)
                .executeAsSingle()
    }

    override fun updateOrderByKey(id: Long, orderByKey: Int) =
            database.updatePassword()
                    .idEq(id)
                    .orderByKey(orderByKey)
                    .executeAsSingle()

    override fun selectMaxOrderByKey(): Single<Int> {
        return Single.create<Int> {
            it.onSuccess(database.selectFromPassword()
                    .maxByOrderByKey() ?: 0)
        }

    }
}