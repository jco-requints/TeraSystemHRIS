package com.example.terasystemhris

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.terasystemhris.databinding.ActivityUpdateBinding
import kotlinx.android.synthetic.main.activity_update.*
import java.util.regex.Pattern


class Update : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        setSupportActionBar(findViewById(R.id.activity_update_toolbar))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)
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
        var profile_name: String

        profile_id_edit.setText(empID_string)
        firstname_edit.setText(firstname_string)
        middlename_edit.setText(middlename_string)
        lastname_edit.setText(lastname_string)
        email_edit.setText(email_string)
        mobile_edit.setText(mobile_string)
        landline_edit.setText(landline_string)
        profile_id_edit.isEnabled = false

        mobile_edit.addTextChangedListener(object : TextWatcher {

            var ignoreChange = false

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!ignoreChange) {
                    var string = s.toString()
                    val mobile_prefix_one_firstCase = "^(09)\\d{2}\$".toRegex()
                    val mobile_prefix_one_secondCase = "^(\\+63)\$".toRegex()
                    val mobile_prefix_two_firstCase = "^(09)\\d{2} \\d{3}\$".toRegex()
                    val mobile_prefix_two_secondCase = "^(\\+63) \\d{3}\$".toRegex()
                    val mobile_prefix_three_secondCase = "^(\\+63) \\d{3} \\d{3}\$".toRegex()
//                    string = string.replace("9", "")
//                    string = string.replace(" ", "hello")
                    if (string.matches(mobile_prefix_one_firstCase))
                    {
                        string += " "
                    }
                    else if (string.matches(mobile_prefix_one_secondCase))
                    {
                        string += " "
                    }
                    if (string.matches(mobile_prefix_two_firstCase))
                    {
                        string += " "
                    }
                    else if (string.matches(mobile_prefix_two_secondCase))
                    {
                        string += " "
                    }
                    if (string.matches(mobile_prefix_three_secondCase))
                    {
                        string += " "
                    }
                    ignoreChange = true
                    mobile_edit.setText(string)
                    mobile_edit.setSelection(mobile_edit.text.length)
                    ignoreChange = false
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val string = s.toString()
                val mobile_prefix_one_firstCase = "^(09)\\d{2}\$".toRegex()
                val mobile_prefix_one_secondCase = "^(\\+63)\$".toRegex()
                val mobile_prefix_two_firstCase = "^(09)\\d{2} \\d{3}\$".toRegex()
                val mobile_prefix_two_secondCase = "^(\\+63) \\d{3}\$".toRegex()
                val mobile_prefix_three_secondCase = "^(\\+63) \\d{3} \\d{3}\$".toRegex()
                ignoreChange = string.matches(mobile_prefix_one_firstCase) || string.matches(mobile_prefix_one_secondCase)
                        || string.matches(mobile_prefix_two_firstCase) || string.matches(mobile_prefix_two_secondCase)
                        || string.matches(mobile_prefix_three_secondCase)
            }
//            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int)
//            }
        })

        landline_edit.addTextChangedListener(PhoneNumberFormattingTextWatcher("PH"))

        binding.updateProfileButton.setOnClickListener {
            val fieldsAreEmpty = checkForEmptyFields()

            val isNumberValid = isMobileNumberValid(mobile_edit.text.toString())

            val isEmailValid = isEmailValid(email_edit.text.toString())

            val isLandlineNumberValid = isLandlineNumberValid(landline_edit.text.toString().replace(" ",""))

            if(!isEmailValid && email_edit.text.trim().toString() != "")
            {
                email_edit.error = "Please enter a valid email"
            }

            if(!isNumberValid && mobile_edit.text.trim().toString() != "")
            {
                mobile_edit.error = "Please enter a valid mobile number"
            }

            if(!isLandlineNumberValid && landline_edit.text.trim().toString() != "")
            {
                landline_edit.error = "Please enter a valid landline number"
            }

            if(!fieldsAreEmpty && isEmailValid && isNumberValid && isLandlineNumberValid)
            {
                saveNewProfile(
                    it, username_string, password_string, empID_string,
                    firstname_edit.text.toString(), middlename_edit.text.toString(), lastname_edit.text.toString(),
                    email_edit.text.toString(), mobile_edit.text.toString(), landline_edit.text.toString())
            }
        }

        binding.closeButton.setOnClickListener {
            finish()
        }
    }

    private fun checkForEmptyFields(): Boolean
    {
        var isEmpty = false
        if (firstname_edit.text.trim().toString() == "")
        {
            firstname_edit.error = "This field cannot be blank"
            isEmpty = true
        }
        if(lastname_edit.text.trim().toString() == "")
        {
            lastname_edit.error = "This field cannot be blank"
            isEmpty = true
        }
        if(email_edit.text.trim().toString() == "")
        {
            email_edit.error = "This field cannot be blank"
            isEmpty = true
        }
        if(mobile_edit.text.trim().toString() == "")
        {
            mobile_edit.error = "This field cannot be blank"
            isEmpty = true
        }
        if(landline_edit.text.trim().toString() == "")
        {
            landline_edit.error = "This field cannot be blank"
            isEmpty = true
        }
        return isEmpty
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isMobileNumberValid(mobile_number: String): Boolean {
            return Pattern.compile(
//                "^(09|\\+639)\\d{9}\$"
                "^((\\+63) \\d{3} \\d{3} \\d{4})|((09)\\d{2} \\d{3} \\d{4})\$"
            ).matcher(mobile_number).matches()
    }

    fun isLandlineNumberValid(landline_number: String): Boolean {
        return Pattern.compile(
            "^(0|\\+63)(2|(3[2-8])|(4[2-9])|(5[2-6])|(6[2-8])|(7[2-8])|(8[2-8]))\\d{7}|\\d{7}\$"
        ).matcher(landline_number).matches()
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
            val intent = Intent(this@Update, Success::class.java).apply {
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
            super.onBackPressed()
            finish()
            true
        } else super.onKeyDown(keyCode, event)
    }
}