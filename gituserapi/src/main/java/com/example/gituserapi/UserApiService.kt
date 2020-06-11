package com.example.andynovelapi.internetApi

import com.example.gituserapi.beans.NetUserBean
import com.example.gituserapi.beans.NetUserDetailBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {
    @GET("users")
    fun getUserInfo(@Query("since") since: Int,
                    @Query("per_page") perPage: Int): Observable<List<NetUserBean>>

    @GET("users/{Login}")
    fun getUserInfoByLogin(@Path("Login") login: String): Observable<NetUserDetailBean>
}