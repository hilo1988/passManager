package com.hiloislay.passmanagerkt.db.realm.repository.impl

import com.hiloislay.passmanagerkt.db.realm.entity.Password
import com.hiloislay.passmanagerkt.db.realm.repository.PasswordRepository
import io.reactivex.Single
import io.realm.RealmResults

class PasswordRepositoryImpl : BaseRepositoryImpl<Password>(), PasswordRepository {


    override fun entityClass() = Password::class.java

    override fun selectList(): RealmResults<Password> {
        return getRealm()
                .where(entityClass())
                .sort("orderByKey")
                .findAllAsync()

    }

    override fun update(entity: Password): Single<Int> {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun updateOrderByKey(id: Long, orderByKey: Int): Single<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selectMaxOrderByKey(): Int {
        return getRealm().where(entityClass())
                .max("orderByKey")
                ?.let { it.toInt() } ?: 0

    }


}