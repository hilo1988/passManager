package com.yoidukigembu.passmanagerkt.model.usecase.impl

import com.yoidukigembu.passmanagerkt.accessor.PasswordDataAccessor
import com.yoidukigembu.passmanagerkt.db.realm.entity.Password
import com.yoidukigembu.passmanagerkt.model.holder.RepositoryHolder
import com.yoidukigembu.passmanagerkt.model.usecase.PasswordUseCase
import io.reactivex.Single
import io.realm.Realm

class PasswordUseCaseImpl : PasswordUseCase {
    override fun createPasswordList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun register(accessor: PasswordDataAccessor): Single<Unit> {
        val entity = Password(accessor)
        val repository = RepositoryHolder.passwordRepository

        return Single.create<Unit> {
            Realm.getDefaultInstance().executeTransaction {
                entity.orderByKey = repository.selectMaxOrderByKey() + 1
                repository.insert(entity)
            }

        }
    }

    override fun update(accessor: PasswordDataAccessor) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}