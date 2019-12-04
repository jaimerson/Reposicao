package com.jaimerson.reposicao

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebClient {
    private var retrofit: Retrofit

    constructor(){
        this.retrofit = Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun usersService() : UsersService{
        return this.retrofit.create(UsersService::class.java)
    }
}

