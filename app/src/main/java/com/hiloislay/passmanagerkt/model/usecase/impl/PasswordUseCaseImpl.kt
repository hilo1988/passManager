package com.hiloislay.passmanagerkt.model.usecase.impl

import com.hiloislay.passmanagerkt.accessor.PasswordDataAccessor
import com.hiloislay.passmanagerkt.db.realm.entity.Password
import com.hiloislay.passmanagerkt.model.holder.RepositoryHolder
import com.hiloislay.passmanagerkt.model.usecase.PasswordUseCase
import com.hiloislay.passmanagerkt.valueobject.Cryptor
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber

class PasswordUseCaseImpl : PasswordUseCase {
    override fun selectPasswords() : RealmResults<Password> {
        return RepositoryHolder.passwordRepository.selectList()
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
                    Timber.w("primaryId:%s", id)
                    id?.let { pId ->
                        Timber.w("piD:%s", pId)
                        RepositoryHolder.passwordRepository.findById(pId)?.let { entity ->
                            Timber.w("entity:%s", entity)
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