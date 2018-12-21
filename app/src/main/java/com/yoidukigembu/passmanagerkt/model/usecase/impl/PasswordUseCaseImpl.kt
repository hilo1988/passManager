package com.yoidukigembu.passmanagerkt.model.usecase.impl

import com.yoidukigembu.passmanagerkt.accessor.PasswordDataAccessor
import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.model.holder.RepositoryHolder
import com.yoidukigembu.passmanagerkt.model.usecase.PasswordUseCase
import io.reactivex.Single

class PasswordUseCaseImpl : PasswordUseCase {
    override fun createPasswordList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun register(accessor: PasswordDataAccessor): Single<Long> {
        val entity = Password(accessor)
        val repository = RepositoryHolder.passwordRepository
        return repository
                .selectMaxOrderByKey()
                .map { entity.also { ent -> ent.orderByKey = it + 1 } }
                .flatMap { repository.insert(it) }
    }

    override fun update(accessor: PasswordDataAccessor) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}