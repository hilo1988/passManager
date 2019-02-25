package com.hiloislay.passmanagerkt.db.realm.repository.impl

import com.hiloislay.passmanagerkt.db.realm.repository.BaseRepository
import io.realm.Realm
import io.realm.RealmObject
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

abstract class BaseRepositoryImpl<E : RealmObject> : BaseRepository<E> {

    protected fun getRealm() = Realm.getDefaultInstance()

    override fun findById(id: Long): E? = getRealm().where(entityClass())
            .equalTo("id", id)
            .findFirst()

    override fun deleteById(id: Long) {
        findById(id)?.deleteFromRealm()
    }

    override fun insert(entity: E) {
        val currentMaxId = (getRealm().where(entityClass())
                .max("id")
                ?: 0).toLong()

        val idProp = entity::class.memberProperties
                .find { it.name == "id" }
        if (idProp is KMutableProperty<*>) {
            idProp.setter.call(entity, currentMaxId + 1)
        }

        getRealm().insert(entity)
    }

    abstract fun entityClass(): Class<E>
}