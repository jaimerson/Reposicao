package com.jaimerson.reposicao

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    companion object {
        var usersList: ArrayList<User> = ArrayList()
        var context : Context? = null
        var callback : () -> Unit = {}

        fun initialize(context: Context, callback : () -> Unit) {
            this.context = context
            refresh()
            this.callback = callback
        }

        fun refresh() {
            this.usersList.clear()
            WebClient().usersService().users().enqueue(object: Callback<ArrayList<User>> {
                override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                    for(user : User in response.body()!!) {
                        usersList.add(user)
                    }
                    callback()
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Toast.makeText(context!!, "foo", Toast.LENGTH_SHORT)
                }
            })
        }

        fun removeUser(item: User) {
        }
    }
}