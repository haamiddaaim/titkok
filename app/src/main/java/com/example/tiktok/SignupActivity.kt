package com.example.tiktok

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiktok.databinding.ActivitySignupBinding
import com.example.tiktok.model.UserModel
import com.example.tiktok.util.UiUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener{
            signup()
    }
        binding.goToLoginBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity:: class.java))
            finish()
        }
}
    fun setInProgress(inProgress : Boolean){
        if (inProgress){
            binding.progressBar.visibility = View.VISIBLE
            binding.submitBtn.visibility = View.GONE
        } else{
            binding.progressBar.visibility = View.GONE
            binding.submitBtn.visibility = View.VISIBLE
        }
    }
fun signup(){
    val email = binding.emailInput.text.toString()
    val password = binding.passwordInput.text.toString()
    val confirmPassword = binding.confirmPasswordInput.text.toString()

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        binding.emailInput.error = "Email not valid"
        return
    }
    if (password.length<6){
        binding.passwordInput.error = "Minimum 6 characters"
        return
    }
    if (password!=confirmPassword) {
        binding.confirmPasswordInput.error = "Password not matched"
    }
    signupWithFirebase(email, password)

    }
    fun signupWithFirebase(email : String, password : String){
        setInProgress(true)
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email,password
        ).addOnSuccessListener {
            it.user?.let { user ->
                val userModel = UserModel(user.uid,email,email.substringBefore("@"))
                Firebase.firestore.collection("users")
                    .document(user.uid)
                    .set(userModel).addOnSuccessListener {
                        UiUtil.showToast(applicationContext, "Account created successfully")
                        setInProgress(false)
                        startActivity(Intent(this,MainActivity :: class.java))
                            finish()

                    }
            }

        }.addOnFailureListener {
            UiUtil.showToast(applicationContext, it.localizedMessage?: "Something went wrong")
            setInProgress(false)
        }
    }
    }