package com.hiloislay.passmanagerkt.model.holder

import com.hiloislay.passmanagerkt.db.realm.repository.PasswordRepository
import com.hiloislay.passmanagerkt.db.realm.repository.impl.PasswordRepositoryImpl


/**
 * リポジトリホルダ
 */
object RepositoryHolder {


    val passwordRepository: PasswordRepository = PasswordRepositoryImpl()

}