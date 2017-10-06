package com.kpiega.daggerscopping.api

import com.kpiega.daggerscopping.model.User
import io.reactivex.Observable

interface TestServiceRepository {

    fun makeTestRequest(user: User): Observable<String>

}