package com.example.andynovelapi.internetApi

import com.example.gituserapi.NetUserBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("users")
    fun getUserInfo(@Query("since") since: Int,
                    @Query("page") page: Int,
                    @Query("per_page") perPage: Int): Observable<List<NetUserBean>>
}