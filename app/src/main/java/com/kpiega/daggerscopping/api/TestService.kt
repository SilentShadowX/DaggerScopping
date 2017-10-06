package com.kpiega.daggerscopping.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

const val SERVER_ADDRESS = "http://37.233.99.29"

interface TestService {

    @POST("api/values")
    fun testService(@Body request: TestRequest): Observable<TestResponse>

}

data class TestResponse(
        @SerializedName("data") val data: String
)

data class TestRequest(
        @SerializedName("data") val data: String
)
