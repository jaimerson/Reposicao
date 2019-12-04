package com.jaimerson.reposicao

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemListView = findViewById<RecyclerView>(R.id.recycler_view)
        itemListView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        UserRepository.initialize(applicationContext) {
            val adapter = UserAdapter(UserRepository.usersList) { position ->
                val dialog = UserDialogFragment {
                    val item = UserRepository.usersList[position]
                    UserRepository.removeUser(item)
                }
                dialog.show(supportFragmentManager, "UserDialogFragment")
            }

            itemListView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}