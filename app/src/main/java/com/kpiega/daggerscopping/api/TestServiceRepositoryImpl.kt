package com.kpiega.daggerscopping.api

import com.kpiega.daggerscopping.model.User
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException

class TestServiceRepositoryImpl(val service: TestService) : TestServiceRepository {

    override fun makeTestRequest(user: User): Observable<String> {
        return service.testService(TestRequest(user.getFullName()))
                .subscribeOn(Schedulers.io())
                .map { "Network response: ${it.data} " }
                .onErrorResumeNext(
                        Function { error ->
                            when (error) {
                                is HttpException -> {
                                    ObservableSource {
                                        it.onNext("HttpException handler")
                                    }
                                }
                                is SocketTimeoutException -> {
                                    ObservableSource {
                                        it.onNext("Timeout exception handler")
                                    }
                                }
                                else -> {
                                    Observable.error(error)
                                }
                            }
                        }
                )
    }

}