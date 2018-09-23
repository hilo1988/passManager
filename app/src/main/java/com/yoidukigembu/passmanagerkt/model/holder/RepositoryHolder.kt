package com.yoidukigembu.passmanagerkt.model.holder

import com.yoidukigembu.passmanagerkt.db.repository.PasswordRepository
import com.yoidukigembu.passmanagerkt.db.repository.impl.PasswordRepositoryImpl

/**
 * リポジトリホルダ
 */
object RepositoryHolder {

    val passwordRepository: PasswordRepository = PasswordRepositoryImpl()

}