package uz.texnopos.qarzdpter.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthHelper(private val auth: FirebaseAuth) {

    private val db = FirebaseFirestore.getInstance()

    fun signIn(
        email: String,
        password: String,
        success: () -> Unit,
        failure: (msg: String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                success.invoke()
            }
            .addOnFailureListener {
                failure.invoke(it.toString())
            }
    }

    fun signUp(
        email: String,
        password: String,
        success: () -> Unit,
        failure: (msg: String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                success.invoke()
            }
            .addOnFailureListener {
                failure.invoke(it.toString())
            }
    }

    fun addData(
        name: String,
        sum: String,
        description: String,
        failure: (msg: String) -> Unit,
        success: () -> Unit
    ) {
        val user = hashMapOf(
            "name" to name,
            "sum" to sum,
            "description" to description
        )
        // Add a new document with a generated ID
        db.collection("contacts")
            .add(user)
            .addOnSuccessListener { documentReference ->
                success.invoke()
            }
            .addOnFailureListener { e ->
                failure.invoke(e.toString())
            }
    }
}