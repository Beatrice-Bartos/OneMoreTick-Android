package com.example.onemoretick.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.onemoretick.R
import com.example.onemoretick.fragments.*
import com.example.onemoretick.fragments.CreateTaskFragment.Companion.TAG_CREATE_TASK
import com.example.onemoretick.fragments.EditTaskFragment.Companion.TAG_EDIT_TASK
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.models.result.LoginUserResponse
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), ActivitiesFragmentsCommunication {
    private var drawerLayout: DrawerLayout? = null
    private var toggle: ActionBarDrawerToggle? = null
    private var toolbar: Toolbar? = null
    var navigationView: NavigationView? = null
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        context = this

        val response = this.intent.getParcelableExtra<LoginUserResponse>("Response")

        var navigationView = findViewById<NavigationView>(R.id.navigationView)
        val headerView = navigationView.getHeaderView(0)
        val navUsername = headerView.findViewById<TextView>(R.id.user_name)
        val navUserEmail = headerView.findViewById<TextView>(R.id.user_email)

        navUserEmail.text = response!!.email

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Dashboard"
        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.navigationView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout!!.addDrawerListener(toggle!!)
        toggle!!.isDrawerIndicatorEnabled = true
        toggle!!.syncState()

        navigationView.setNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.all_tasks) {
                Toast.makeText(context, "All tasks", Toast.LENGTH_SHORT).show();
//                onReplaceFragment(TAG_WALKS)
            }
            if (item.itemId == R.id.create_task) {
                Toast.makeText(context, "Create task", Toast.LENGTH_SHORT).show();
                onReplaceFragment(TAG_CREATE_TASK, response.id)
            }
            if (item.itemId == R.id.shopping_lists) {
                Toast.makeText(context, "Shopping lists", Toast.LENGTH_SHORT).show();
//                onReplaceFragment(TAG_FIND)
            }
            if (item.itemId == R.id.create_shopping_list) {
                Toast.makeText(context, "Create Shopping list", Toast.LENGTH_SHORT).show();
//                onReplaceFragment(TAG_EDIT)
            }
            if (item.itemId == R.id.logout) {
                Toast.makeText(context, "Logout success!", Toast.LENGTH_SHORT).show();
                goToWelcome()
            }
            false
        }

        if (savedInstanceState == null) {
//            onAddFragment()
        }
    }

//    private fun onAddFragment() {
//        val fragmentManager = supportFragmentManager
//        val fragment: Fragment
//        fragment = EditTaskFragment.newInstance()
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.add(R.id.container_fragment, fragment, TAG_EDIT_TASK)
//        fragmentTransaction.commit()
//    }

    private fun goToWelcome() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onReplaceFragment(TAG: String?, userId: Int?) {
        val fragmentManager = supportFragmentManager

        val fragment: Fragment = when (TAG) {
            CreateTaskFragment.TAG_CREATE_TASK -> {
                CreateTaskFragment.newInstance(userId!!)
            }
            EditTaskFragment.TAG_EDIT_TASK -> {
                EditTaskFragment.newInstance(userId!!)
            }
            WelcomeFragment.TAG_WELCOME -> {
                WelcomeFragment.newInstance()
            }
            else -> return
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragment, TAG)

        fragmentTransaction.addToBackStack(TAG)
        fragmentTransaction.commit()
    }
}