package com.example.terasystemhris

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.terasystemhris.databinding.ActivitySuccessBinding
import com.example.terasystemhris.databinding.ActivityUpdateBinding
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP



class Success : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
//        val actionBar = supportActionBar // or getActionBar();
//        supportActionBar!!.title = "Success" // set the top title
//        val title = actionBar!!.title.toString() // get the title
        setSupportActionBar(findViewById(R.id.activity_success_toolbar))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_success)
        val intent = intent
        val extras = intent.extras
        val username_string = intent.getStringExtra("EXTRA_USERNAME")
        val password_string = extras?.getString("EXTRA_PASSWORD")
        val empID_string = extras?.getString("EXTRA_EMPID")
        val firstname_string = extras?.getString("EXTRA_FIRSTNAME")
        val middlename_string = extras?.getString("EXTRA_MIDDLENAME")
        val lastname_string = extras?.getString("EXTRA_LASTNAME")
        val email_string = extras?.getString("EXTRA_EMAIL")
        val mobile_string = extras?.getString("EXTRA_MOBILE")
        val landline_string = extras?.getString("EXTRA_LANDLINE")

        binding.okButton.setOnClickListener {
            saveNewProfile(it, username_string, password_string, empID_string,
                firstname_string, middlename_string, lastname_string,
                email_string, mobile_string, landline_string)
        }


    }

    private fun saveNewProfile(
        view: View,
        username_string: String?,
        password_string: String?,
        empID_string: String?,
        firstname_string: String?,
        middlename_string: String?,
        lastname_string: String?,
        email_string: String?,
        mobile_string: String?,
        landline_string: String?)
    {


        binding.apply {
            val intent = Intent(this@Success, profile::class.java).apply {
                val extras = Bundle()
                extras.putString("EXTRA_USERNAME", username_string)
                extras.putString("EXTRA_PASSWORD", password_string)
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

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = intent
            val extras = intent.extras
            val username_string = intent.getStringExtra("EXTRA_USERNAME")
            val password_string = extras?.getString("EXTRA_PASSWORD")
            val empID_string = extras?.getString("EXTRA_EMPID")
            val firstname_string = extras?.getString("EXTRA_FIRSTNAME")
            val middlename_string = extras?.getString("EXTRA_MIDDLENAME")
            val lastname_string = extras?.getString("EXTRA_LASTNAME")
            val email_string = extras?.getString("EXTRA_EMAIL")
            val mobile_string = extras?.getString("EXTRA_MOBILE")
            val landline_string = extras?.getString("EXTRA_LANDLINE")
            binding.apply {
                val intent = Intent(this@Success, profile::class.java).apply {
                    val extras = Bundle()
                    extras.putString("EXTRA_USERNAME", username_string)
                    extras.putString("EXTRA_PASSWORD", password_string)
                    extras.putString("EXTRA_EMPID", empID_string)
                    extras.putString("EXTRA_FIRSTNAME", firstname_string)
                    extras.putString("EXTRA_MIDDLENAME", middlename_string)
                    extras.putString("EXTRA_LASTNAME", lastname_string)
                    extras.putString("EXTRA_EMAIL", email_string)
                    extras.putString("EXTRA_MOBILE", mobile_string)
                    extras.putString("EXTRA_LANDLINE", landline_string)
                    this.putExtras(extras)
                    this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
            true
        } else super.onKeyDown(keyCode, event)
    }

}
