package com.yoidukigembu.passmanagerkt.model.usecase.impl

import com.yoidukigembu.passmanagerkt.accessor.PasswordDataAccessor
import com.yoidukigembu.passmanagerkt.db.entity.Password
import com.yoidukigembu.passmanagerkt.model.holder.RepositoryHolder
import com.yoidukigembu.passmanagerkt.model.usecase.PasswordUseCase

class PasswordUseCaseImpl : PasswordUseCase {
    override fun createPasswordList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun register(accessor: PasswordDataAccessor): Long {
        val entity = Password(accessor)
        val repository = RepositoryHolder.passwordRepository
        entity.orderByKey = repository.selectMaxOrderByKey() + 1
        return repository.insert(entity)
    }

    override fun update(accessor: PasswordDataAccessor) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}