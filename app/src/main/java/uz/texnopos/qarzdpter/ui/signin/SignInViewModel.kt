package uz.texnopos.qarzdpter.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.texnopos.qarzdpter.helper.AuthHelper

class SignInViewModel(private val authHelper: AuthHelper) : ViewModel() {

    var signIn: MutableLiveData<String> = MutableLiveData()

    fun signIn(email: String, password: String) {
        signIn.value = "loading"
        authHelper.signIn(email, password,
            success = { signIn.value = "success" },
            failure = { signIn.value = it })
    }
}