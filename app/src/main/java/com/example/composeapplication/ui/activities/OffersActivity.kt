package com.example.composeapplication.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.composeapplication.ui.ViewModel.OffersViewModel
import com.example.composeapplication.ui.screens.OffersList
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class OffersActivity : ComponentActivity() {

    private val loginViewModel by viewModels<OffersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    OffersList(loginViewModel)
                }
            }
        }
    }
}