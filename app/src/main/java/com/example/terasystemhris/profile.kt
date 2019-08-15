package com.example.terasystemhris

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.terasystemhris.databinding.ActivityProfileBinding
import kotlinx.android.synthetic.main.activity_profile.*

class profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(findViewById(R.id.activity_profile_toolbar))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        val intent = intent
        val extras = intent.extras
        val username_string = extras?.getString("EXTRA_USERNAME")
        val password_string = extras?.getString("EXTRA_PASSWORD")
        val empID_string = extras?.getString("EXTRA_EMPID")
        val firstname_string = extras?.getString("EXTRA_FIRSTNAME")
        val middlename_string = extras?.getString("EXTRA_MIDDLENAME")
        val lastname_string = extras?.getString("EXTRA_LASTNAME")
        val email_string = extras?.getString("EXTRA_EMAIL")
        val mobile_string = extras?.getString("EXTRA_MOBILE")
        val landline_string = extras?.getString("EXTRA_LANDLINE")
        val profile_name: String

        if(middlename_string != "")
        {
            profile_name = ("$firstname_string $middlename_string $lastname_string").toUpperCase()
        }
        else
        {
            profile_name = ("$firstname_string $lastname_string").toUpperCase()
        }

        name_text.text = profile_name
        val firstNameInitial = firstname_string?.get(0).toString()
        val lastNameInitial = lastname_string?.get(0).toString()
        profile_id.text = empID_string
        profile_email.text = maskEmail(email_string)
        profile_number.text = maskMobileNumber(mobile_string)
        initials.text = "$firstNameInitial$lastNameInitial"

        binding.updateButton.setOnClickListener {
            updateProfile(it, username_string, password_string, empID_string,
                firstname_string, middlename_string, lastname_string,
                email_string, mobile_string, landline_string)
        }
        binding.logoutButton.setOnClickListener{
            val intent = Intent(this@profile, MainActivity::class.java).apply {
                this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }

    private fun maskEmail(email: String?): String{
        val email_id: String? = email?.substringBeforeLast("@")
        var masked_email: String = ""
        val email_initials = email?.substring(0..3)
        val hide_email = email?.replaceBefore('@', "*****")
        if(email_id!!.count() <= 3)
        {
            masked_email = "$email_id$hide_email"
        }
        else if(email_id!!.count() > 3)
        {
            masked_email = "$email_initials$hide_email"
        }
        return  masked_email
    }

    private fun maskMobileNumber(mobile: String?): String{
        val country_code: String
        val mobile_initials: String
        val hide_mobile: String
        var masked_mobile: String = ""
        if(mobile?.count() == 16)
        {
            country_code = mobile.substring(0..2)
            mobile_initials = mobile.substring(3..7)
            hide_mobile = mobile.substring(11..15)
            masked_mobile = "$country_code$mobile_initials***$hide_mobile"
        }
        else if(mobile?.count() == 13)
        {
            mobile_initials = mobile.substring(0..4)
            hide_mobile = mobile.substring(8..12)
            masked_mobile = "$mobile_initials***$hide_mobile"
        }
        return  masked_mobile
    }

    private fun updateProfile(
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
            val intent = Intent(this@profile, Update::class.java).apply {
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

    override
    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            super.onBackPressed()
            val intent = Intent(this@profile, MainActivity::class.java).apply {
                this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            true
        } else super.onKeyDown(keyCode, event)
    }
}
