package com.hiloislay.passmanagerkt.model.holder

import com.hiloislay.passmanagerkt.db.realm.entity.Password
import io.reactivex.subjects.PublishSubject

object SubjectHolder {

    /**
     * パスワードデータが変更された時のサブジェクト
     */
    val onPasswordChangedSubject = PublishSubject.create<Password>()
}