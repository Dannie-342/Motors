package com.example.motors.data

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.motors.model.UserModel
import com.example.motors.navigation.ROUTE_HOME
import com.example.motors.navigation.ROUTE_LOGIN
import com.example.motors.navigation.ROUTE_REGISTER
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow



class AuthviewModel: ViewModel() {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    fun signup(
        firstname: String, lastname: String, phone: String, email: String, password: String,
        navController: NavController,
        context: Context
    ) {

        if (firstname.isBlank() || lastname.isBlank() || phone.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()

            return
        }
        _isLoading.value = true

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    val userId = mAuth.currentUser?.uid ?: ""
                    val userData = UserModel(
                        firstname = firstname,
                        lastname = lastname,
                        phone = phone,
                        email = email,
                        password = password,
                        userId = userId,
                    )
                    saveUserToDatabase(userId, userData, navController, context)


                } else {
                    _errorMessage.value = task.exception?.message

                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun saveUserToDatabase(
        userId: String, userData: UserModel,
        navController: NavController, context: Context
    ) {
        val regRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
        regRef.setValue(userData).addOnCompleteListener { regRef ->
            if (regRef.isSuccessful) {
                Toast.makeText(context, "User Successfully Registered", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            } else {
                _errorMessage.value = regRef.exception?.message
                Toast.makeText(context, "Database error", Toast.LENGTH_LONG).show()
            }
        }

    }


    fun login(email: String, password: String, navController: NavController, context: Context) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
            return
        }
        _isLoading.value = true
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    Toast.makeText(context, "Logged in successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_HOME)
                } else {
                    _errorMessage.value = task.exception?.message
                    Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
                }
            }

    }

    fun reset(email: String, context: Context, navController: NavController) {
        if (email.isBlank()) {
            Toast.makeText(context, "Email required", Toast.LENGTH_LONG).show()
            return
        }
        _isLoading.value = true
        mAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    Toast.makeText(context, "Email sent successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_LOGIN)
                } else {
                    _errorMessage.value = task.exception?.message
                    Toast.makeText(context, "Email not sent", Toast.LENGTH_LONG).show()
                }
            }

    }
    fun loginWithGoogle(navController: NavController, context: Context) {
        if (
            mAuth.currentUser?.displayName == null ||
            mAuth.currentUser?.email == null ||
            mAuth.currentUser?.photoUrl == null
        ) {
            Toast.makeText(context, "Please login with Google", Toast.LENGTH_LONG).show()
            return
        }
        val userId = mAuth.currentUser?.uid ?: ""
        val userData = UserModel(
            firstname = mAuth.currentUser?.displayName ?: "",
            lastname = "",
            phone = "",
            email = mAuth.currentUser?.email ?: "",
            password = "",
            userId = userId,
        )
        saveUserToDatabase(userId, userData, navController, context)
        Toast.makeText(context, "Logged in successfully", Toast.LENGTH_LONG).show()
        navController.navigate(ROUTE_HOME)
    }

    fun loginWithFacebook(navController: NavController, context: Context) {
        if (
            mAuth.currentUser?.displayName == null ||
            mAuth.currentUser?.email == null ||
            mAuth.currentUser?.photoUrl == null
        ) {
            Toast.makeText(context, "Please login with Facebook", Toast.LENGTH_LONG).show()
            return
        }
        val userId = mAuth.currentUser?.uid ?: ""
        val userData = UserModel(
            firstname = mAuth.currentUser?.displayName ?: "",
            lastname = "",
            phone = "",
            email = mAuth.currentUser?.email ?: "",
            password = "",
            userId = userId,
        )
        saveUserToDatabase(userId, userData, navController, context)
        Toast.makeText(context, "Logged in successfully", Toast.LENGTH_LONG).show()
        navController.navigate(ROUTE_HOME)
    }




    fun search(navController: NavController, context: Context) {
        Toast.makeText(context, "Search clicked", Toast.LENGTH_LONG).show()
        return
    }


    fun add(navController: NavController, context: Context) {
        Toast.makeText(context, "Add clicked", Toast.LENGTH_LONG).show()
        navController.navigate(ROUTE_HOME)
    }

    fun logout(navController: NavController, context: Context) {
        mAuth.signOut()
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_LONG).show()
        navController.navigate(ROUTE_REGISTER) {
            popUpTo(ROUTE_HOME) {
                inclusive = true
            }


        }

    }
     fun menu(navController: NavController, context: Context) {
         Toast.makeText(context, "Menu clicked", Toast.LENGTH_LONG).show()
         navController.navigate(ROUTE_HOME)
     }








}








