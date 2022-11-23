package com.example.composeapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.R
import com.example.composeapplication.ui.ViewModel.LoginViewModel
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val errorUsername by viewModel.errorUsername.observeAsState(R.string.empty)
    val errorPassword by viewModel.errorPassword.observeAsState(R.string.empty)
    val successMessage by viewModel.successMessage.observeAsState(R.string.empty)

    Surface(modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))) {
        Column {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Text(text = stringResource(id = R.string.username))
            TextField(
                onValueChange = { username = it },
                value = username,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(text = stringResource(errorUsername), color = Color.Red, modifier = Modifier.padding(bottom = 40.dp))

            Text(text = stringResource(id = R.string.password))
            TextField(
                onValueChange = { password = it },
                value = password,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(text = stringResource(errorPassword), color = Color.Red, modifier = Modifier.padding(bottom = 40.dp))

            Button(
                onClick = { viewModel.onSignIn.invoke(username, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.sign_in))
            }
            Text(
                text = stringResource(successMessage),
                color = if (successMessage == R.string.login_success) Color.Green else Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {
        LoginScreen(LoginViewModel())
    }
}