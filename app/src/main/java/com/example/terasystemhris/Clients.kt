package com.example.terasystemhris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.terasystemhris.databinding.ActivityClientsBinding
import kotlinx.android.synthetic.main.activity_profile.*

class Clients : AppCompatActivity() {

    private lateinit var binding: ActivityClientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)
        setSupportActionBar(findViewById(R.id.activity_clients_toolbar))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_clients)
        val intent = intent
        val extras = intent.extras
        val username_string = extras?.getString("EXTRA_USERNAME")
        val empID_string = extras?.getString("EXTRA_EMPID")
        val firstname_string = extras?.getString("EXTRA_FIRSTNAME")
        val middlename_string = extras?.getString("EXTRA_MIDDLENAME")
        val lastname_string = extras?.getString("EXTRA_LASTNAME")
        val email_string = extras?.getString("EXTRA_EMAIL")
        val mobile_string = extras?.getString("EXTRA_MOBILE")
        val landline_string = extras?.getString("EXTRA_LANDLINE")

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logs -> {
                    binding.apply {
                        val intent = Intent(this@Clients, Logs::class.java).apply {
                            val extras = Bundle()
                            extras.putString("EXTRA_USERNAME", username_string)
                            extras.putString("EXTRA_EMPID", empID_string)
                            extras.putString("EXTRA_FIRSTNAME", firstname_string)
                            extras.putString("EXTRA_MIDDLENAME", middlename_string)
                            extras.putString("EXTRA_LASTNAME", lastname_string)
                            extras.putString("EXTRA_EMAIL", email_string)
                            extras.putString("EXTRA_MOBILE", mobile_string)
                            extras.putString("EXTRA_LANDLINE", landline_string)
                            this.putExtras(extras)
                        }
                        startActivity(intent)
                    }
                    true
                }
                R.id.leaves -> {
                    binding.apply {
                        val intent = Intent(this@Clients, Leaves::class.java).apply {
                            val extras = Bundle()
                            extras.putString("EXTRA_USERNAME", username_string)
                            extras.putString("EXTRA_EMPID", empID_string)
                            extras.putString("EXTRA_FIRSTNAME", firstname_string)
                            extras.putString("EXTRA_MIDDLENAME", middlename_string)
                            extras.putString("EXTRA_LASTNAME", lastname_string)
                            extras.putString("EXTRA_EMAIL", email_string)
                            extras.putString("EXTRA_MOBILE", mobile_string)
                            extras.putString("EXTRA_LANDLINE", landline_string)
                            this.putExtras(extras)
                        }
                        startActivity(intent)
                    }
                    true
                }
                R.id.me -> {
                    binding.apply {
                        val intent = Intent(this@Clients, Profile::class.java).apply {
                            val extras = Bundle()
                            extras.putString("EXTRA_USERNAME", username_string)
                            extras.putString("EXTRA_EMPID", empID_string)
                            extras.putString("EXTRA_FIRSTNAME", firstname_string)
                            extras.putString("EXTRA_MIDDLENAME", middlename_string)
                            extras.putString("EXTRA_LASTNAME", lastname_string)
                            extras.putString("EXTRA_EMAIL", email_string)
                            extras.putString("EXTRA_MOBILE", mobile_string)
                            extras.putString("EXTRA_LANDLINE", landline_string)
                            this.putExtras(extras)
                        }
                        startActivity(intent)
                    }
                    true
                }
                else -> false
            }
        } //end of OnNavigationItemSelectedListener
    }
}
