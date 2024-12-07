package com.example.tiktok

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiktok.databinding.ActivityForgotPasswordBinding
import com.example.tiktok.databinding.ActivityLoginBinding
import com.example.tiktok.databinding.ActivitySignupBinding
import com.example.tiktok.model.UserModel
import com.example.tiktok.util.UiUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var binding : ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendemailBtn.setOnClickListener {
            sendemail()
    }
    }
  fun sendemail() {

          val email = binding.emailInput.text.toString()

          if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
              binding.emailInput.error = "Email not valid"
              return
          }
          sendemailWithFirebase(email)


      }

    fun sendemailWithFirebase(email: String) {
        setInProgress(true)

        FirebaseAuth.getInstance().sendPasswordResetEmail(
            email
        ).addOnSuccessListener {
            UiUtil.showToast(applicationContext, "Email sent successfully")
                        setInProgress(false)
                        finish()

        }.addOnFailureListener {
            UiUtil.showToast(applicationContext, it.localizedMessage?: "Something went wrong")
            setInProgress(false)
        }
    }

    fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.sendemailBtn.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.sendemailBtn.visibility = View.VISIBLE
        }}
}