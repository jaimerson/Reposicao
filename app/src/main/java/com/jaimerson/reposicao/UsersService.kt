package com.jaimerson.reposicao

import retrofit2.Call
import retrofit2.http.GET

interface UsersService {
    @GET("users")
    fun users() : Call<ArrayList<User>>
}

