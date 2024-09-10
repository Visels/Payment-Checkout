package com.lemu.pay.checkout.login

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemu.pay.checkout.data.local.PreferencesHelper
import com.lemu.pay.checkout.data.models.AuthenticationRequest
import com.lemu.pay.checkout.data.models.AuthenticationResponse
import com.lemu.pay.checkout.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkoutRepository: CheckoutRepository,
    val preferencesHelper: PreferencesHelper
) : ViewModel() {

    private val _loginUIScreenState = MutableStateFlow<LoginUIScreenState>(LoginUIScreenState.INITIAL)
    val loginUiScreenState:StateFlow<LoginUIScreenState> = _loginUIScreenState

    fun login(username:String, password:String) = viewModelScope.launch {
        _loginUIScreenState.value = LoginUIScreenState.LOADING
        val request = AuthenticationRequest(
            username = username,
            password = password
        )
        checkoutRepository.login(request)
            .catch {
                _loginUIScreenState.value = LoginUIScreenState.Error("Wrong username or password")
            }
            .collect{
                response ->
                run {
                    if (response?.authenticated == true) {
                        _loginUIScreenState.value = LoginUIScreenState.SUCCESS(response)

                        preferencesHelper.putToken(response.base64EncodedAuthenticationKey)
                        preferencesHelper.putLastLoginDate(LocalDate.now().toString())
                        Log.d("SAVED VALUES","Token: ${preferencesHelper.token} Date: ${preferencesHelper.lastLoginDate}")
                    }
                    else {
                        _loginUIScreenState.value = LoginUIScreenState.Error("Wrong username or password")
                    }
                }
        }

    }

    sealed class LoginUIScreenState{

        object INITIAL : LoginUIScreenState()

        object LOADING: LoginUIScreenState()

        class SUCCESS(val response:AuthenticationResponse):LoginUIScreenState()

        class Error(val message:String):LoginUIScreenState()
    }


}