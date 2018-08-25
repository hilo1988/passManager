package com.yoidukigembu.passmanagerkt.db.repository.impl

import com.yoidukigembu.passmanagerkt.db.entity.OrmaDatabase

abstract class BaseRepositoryImpl<E> {
    companion object {
        lateinit  var database:OrmaDatabase
    }
}