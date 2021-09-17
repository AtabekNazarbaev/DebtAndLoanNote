package uz.texnopos.qarzdpter.ui.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.texnopos.qarzdpter.helper.AuthHelper

class SignUpViewModel(private val authHelper: AuthHelper) : ViewModel() {

    var signUp: MutableLiveData<String> = MutableLiveData()

    fun signUp(email: String, password: String) {
        signUp.value = "loading"
        authHelper.signUp(email, password,
            success = { signUp.value = "success" },
            failure = { signUp.value = it })
    }
}