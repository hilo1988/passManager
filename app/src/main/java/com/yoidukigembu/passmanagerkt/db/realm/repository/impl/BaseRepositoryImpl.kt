package com.yoidukigembu.passmanagerkt.db.realm.repository.impl

import com.yoidukigembu.passmanagerkt.db.realm.repository.BaseRepository
import io.realm.Realm
import io.realm.RealmObject

abstract class BaseRepositoryImpl<E : RealmObject> : BaseRepository<E> {

    protected fun getRealm() = Realm.getDefaultInstance()

    override fun findById(id: Long): E? = getRealm().where(entityClass())
            .equalTo("id", id)
            .findFirst()

    override fun deleteById(id: Long) {
        findById(id)?.deleteFromRealm()
    }

    override fun insert(entity: E) {
        getRealm().insert(entity)
    }

    abstract fun entityClass(): Class<E>
}