package com.yoidukigembu.passmanagerkt.model.holder

import com.yoidukigembu.passmanagerkt.db.realm.repository.PasswordRepository
import com.yoidukigembu.passmanagerkt.db.realm.repository.impl.PasswordRepositoryImpl


/**
 * リポジトリホルダ
 */
object RepositoryHolder {


    val passwordRepository: PasswordRepository = PasswordRepositoryImpl()

}