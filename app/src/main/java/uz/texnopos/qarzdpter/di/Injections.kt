package uz.texnopos.qarzdpter.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.texnopos.qarzdpter.helper.AuthHelper
import uz.texnopos.qarzdpter.ui.main.MainFragmentViewModel
import uz.texnopos.qarzdpter.ui.signUp.SignUpViewModel
import uz.texnopos.qarzdpter.ui.signin.SignInViewModel

val dataModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
}
val helper = module {
    single { AuthHelper(get()) }
}
val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { MainFragmentViewModel(get()) }
}