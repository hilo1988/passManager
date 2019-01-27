package com.hiloislay.passmanagerkt.model.usecase.impl

import com.hiloislay.passmanagerkt.accessor.PasswordDataAccessor
import com.hiloislay.passmanagerkt.db.realm.entity.Password
import com.hiloislay.passmanagerkt.model.holder.RepositoryHolder
import com.hiloislay.passmanagerkt.model.usecase.PasswordUseCase
import com.hiloislay.passmanagerkt.util.Logger
import com.hiloislay.passmanagerkt.valueobject.Cryptor
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

        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    val id = accessor.getPrimaryId()
                    com.hiloislay.passmanagerkt.util.Logger.w("primaryId:%s", id)
                    id?.let { pId ->
                        com.hiloislay.passmanagerkt.util.Logger.w("piD:%s", pId)
                        RepositoryHolder.passwordRepository.findById(pId)?.let { entity ->
                            com.hiloislay.passmanagerkt.util.Logger.w("entity:%s", entity)
                            val cryptor = Cryptor.getInstance()
                            entity.name = accessor.getLabelName() ?: ""
                            entity.loginId = accessor.getLoginId() ?: ""
                            entity.loginUrl = accessor.getLoginUrl() ?: ""
                            entity.password1 = accessor.getPassword1()?.let { cryptor.encrypt(it) }
                            entity.password2 = accessor.getPassword2()?.let { cryptor.encrypt(it) }
                            entity.memo = accessor.getMemo() ?: ""
                            realm.copyToRealmOrUpdate(entity)
                        }
                    }

                }
    }
}