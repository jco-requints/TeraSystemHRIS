package com.example.terasystemhris

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.terasystemhris.databinding.ActivityMainBinding
import android.widget.Toast
import android.content.Intent
import android.view.KeyEvent
import android.webkit.WebSettings
import kotlinx.android.synthetic.main.activity_webview.*
import android.view.KeyEvent.KEYCODE_BACK
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.loginButton.setOnClickListener {
            if (userId_edit.text.trim().toString() == "")
            {
                userId_edit.setError("User ID is required")

            }
            if(password_edit.text.trim().toString() == "")
            {
                password_edit.setError("Password is required")
            }
            if(userId_edit.text.trim().toString() != "" && password_edit.text.trim().toString() != "")
            authenticateCredentials(it)
        }
        binding.signupButton.setOnClickListener {
            setContentView(R.layout.activity_webview)
            val settings = webview.settings
            // Enable java script in web view
            settings.javaScriptEnabled = true

            // Enable and setup web view cache
            settings.setAppCacheEnabled(true)
            settings.cacheMode = WebSettings.LOAD_DEFAULT
            settings.setAppCachePath(cacheDir.path)


            // Enable zooming in web view
            settings.setSupportZoom(true)
            settings.builtInZoomControls = true
            settings.displayZoomControls = true
            webview.loadUrl("http://www.terasystem.com")
        }
    }

    private fun authenticateCredentials(view: View) {


        binding.apply {
            var isValid = true
            for(account in list)
            {
                if(userIdEdit.text.toString() == account.userID && passwordEdit.text.toString() == account.password)
                {
                    val intent = Intent(this@MainActivity, profile::class.java).apply {
                        val extras = Bundle()
                        extras.putString("EXTRA_USERNAME", account.userID)
                        extras.putString("EXTRA_PASSWORD", account.password)
                        extras.putString("EXTRA_EMPID", account.empID)
                        extras.putString("EXTRA_FIRSTNAME", account.firstname)
                        extras.putString("EXTRA_MIDDLENAME", account.middlename)
                        extras.putString("EXTRA_LASTNAME", account.lastname)
                        extras.putString("EXTRA_EMAIL", account.email)
                        extras.putString("EXTRA_MOBILE", account.mobile)
                        extras.putString("EXTRA_LANDLINE", account.landline)
                        this.putExtras(extras)
                    }
                    startActivity(intent)
                    isValid = true
                    break
//                    startActivity(Intent(this@MainActivity, profile::class.java).apply {
//                        putExtras(bundleOf("EXTRA_USERNAME" to account.userID,
//                            "EXTRA_PASSWORD" to account.password))
//                    })
                }
                else
                {
                    isValid = false
                }
            }
            if(!isValid)
            {
                val toast = Toast.makeText(
                    applicationContext,
                    "Invalid user name or password",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override
    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KEYCODE_BACK) {
            super.onBackPressed()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            true
        } else super.onKeyDown(keyCode, event)
    }

}
