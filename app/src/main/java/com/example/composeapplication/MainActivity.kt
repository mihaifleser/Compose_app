package com.example.composeapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.composeapplication.ui.ViewModel.LoginViewModel
import com.example.composeapplication.ui.activities.OffersActivity
import com.example.composeapplication.ui.screens.LoginScreen
import com.example.composeapplication.ui.theme.ComposeApplicationTheme


class MainActivity : ComponentActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    LoginScreen(loginViewModel)
                }
            }
        }
        loginViewModel.navigateNext.observe(this) {
            if (it == 1) {
                this.startActivity(Intent(this, OffersActivity::class.java))
            }
        }
    }
}