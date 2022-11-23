package com.example.composeapplication.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bachelor_app.util.SingleLiveEvent
import com.example.composeapplication.R

class LoginViewModel : ViewModel() {

    private val _errorUsername = MutableLiveData<Int>()
    val errorUsername: LiveData<Int> = _errorUsername

    private val _errorPassword = MutableLiveData<Int>()
    val errorPassword: LiveData<Int> = _errorPassword

    private val _successMessage = MutableLiveData<Int>()
    val successMessage: LiveData<Int> = _successMessage

    private val _navigateNext = SingleLiveEvent<Int>()
    val navigateNext: SingleLiveEvent<Int> = _navigateNext

    val onSignIn: (username: String, password: String) -> Unit = { username, password ->
        println("$username $password")
        var errorUserString = R.string.empty
        var errorPasswordString = R.string.empty

        when {
            username == "" -> errorUserString = R.string.username_empty
            username.length < 3 -> errorUserString = R.string.username_short
        }

        when {
            password == "" -> errorPasswordString = R.string.password_empty
            password.length < 3 -> errorPasswordString = R.string.password_short
        }

        val successString: Int = if (errorUserString == R.string.empty && errorPasswordString == R.string.empty) {
            R.string.login_success
        } else {
            R.string.login_failed
        }
        _errorUsername.postValue(errorUserString)
        _errorPassword.postValue(errorPasswordString)
        _successMessage.postValue(successString)

        successString.takeIf { it == R.string.login_success }?.let { _navigateNext.postValue(1) }
    }
}