package com.yoidukigembu.passmanagerkt.model.holder

import com.yoidukigembu.passmanagerkt.db.realm.entity.Password
import io.reactivex.subjects.PublishSubject

object SubjectHolder {

    /**
     * パスワードデータが変更された時のサブジェクト
     */
    val onPasswordChangedSubject = PublishSubject.create<Password>()
}