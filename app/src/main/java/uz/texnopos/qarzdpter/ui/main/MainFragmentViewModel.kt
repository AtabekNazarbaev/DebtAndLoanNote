package uz.texnopos.qarzdpter.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.texnopos.qarzdpter.helper.AuthHelper

class MainFragmentViewModel(private val authHelper: AuthHelper) : ViewModel() {

    val addUser: MutableLiveData<String> = MutableLiveData()

    fun addUser(name: String, sum: String, description: String) {
        addUser.value = "loading"
        authHelper.addData(name, sum, description, failure = {
            addUser.value = it
        }, success = {
            addUser.value = "success"
        })
    }
}