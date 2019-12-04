package com.jaimerson.reposicao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.opt_profile -> {
                true
            }
            R.id.opt_logout -> {
                true
            }
            else -> {
                false
            }
        }
    }

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