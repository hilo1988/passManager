package com.hiloislay.passmanagerkt.db.realm.repository

import io.realm.RealmObject

interface BaseRepository<E : RealmObject> {

    fun findById(id: Long): E?

    fun deleteById(id: Long)

    /**
     * データのインサート
     */
    fun insert(entity: E)
}