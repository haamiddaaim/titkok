package com.example.tiktok

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tiktok.databinding.ActivityLoginBinding
import com.example.tiktok.util.UiUtil
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Arrays


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val googleSignInRequestCode = 234
    lateinit var auth: FirebaseAuth
    lateinit var loginmanager: LoginManager
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var callbackMananger: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      /*  try {
            val info = packageManager.getPackageInfo(
                "com.example.tiktok",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }*/

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        FacebookSdk.sdkInitialize(applicationContext)
        callbackMananger = CallbackManager.Factory.create()
        loginmanager = LoginManager.getInstance()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val currentUser = auth.currentUser
        googleSignInClient = GoogleSignIn.getClient(this, gso)

      /*  if (currentUser != null) {
            // The user is already signed in, navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // finish the current activity to prevent the user from coming back to the SignInActivity using the back button
        }*/



        binding.submitBtn.setOnClickListener {
            login()
        }
        binding.goToSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        binding.goToForgotpasswordBtn.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        binding.google.setOnClickListener {
            signInwithGoogle()
        }
        binding.facebook.setOnClickListener {
            signInwithFacebook()
        }


    }



    private fun signInwithFacebook() {
        loginmanager.logInWithReadPermissions(this, listOf("email","public_profile"))
        //loginmanager.logInWithReadPermissions(this, Arrays.asList("email","public_profile"))
        loginmanager.registerCallback(
            callbackMananger,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {

                /** Log.d("MainActivity", "Hello, this is our first log message")
                    a better way to print something in logcat by using the fun that are created for that logcat
                    d stands for debug...e stands for error and so on
                    it gives your message a tag that you can just search for to find easily in logcat**/


                    // Log.d(TAG, "facebook:onSuccess:$loginResult")
                    //startActivity(Intent(this, MainActivity::class.java))
                    handleFacebookAccessToken(loginResult.accessToken)
                }
                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "facebook:onError", error)
                }

            },

        )
        //login()




    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackMananger.onActivityResult(requestCode, resultCode, data)
    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackMananger.onActivityResult(requestCode, resultCode, data)
    }*/
   /* public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
      //  updateFacebookUI(currentUser)
    }*/




    private fun handleFacebookAccessToken(token: AccessToken) {
        //Log.d(TAG, "handleFacebookAccessToken:$token")
        setInProgress(true)
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    // Log.d(TAG, "signInWithCredential:success")
                    //val user = auth.currentUser
                   // updateFacebookUI(user)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    setInProgress(false)
                } else {
                    UiUtil.showToast(this, "Can't login currently. Try after sometime")
                    setInProgress(false)
                   // updateFacebookUI(null)
                }
            }
    }





    private fun signInwithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
        startActivityForResult(signInIntent, googleSignInRequestCode)
    }



    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }

        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        setInProgress(true)
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                setInProgress(false)
            } else {
                UiUtil.showToast(this, "Can't login currently. Try after sometime")
                setInProgress(false)
            }
        }

    }

    fun login() {
        val email = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInput.error = "Email not valid"
            return
        }
        if (password.length < 6) {
            binding.passwordInput.error = "Minimum 6 characters"
            return
        }
        loginWithFirebase(email, password)


    }

    fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.submitBtn.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.submitBtn.visibility = View.VISIBLE


        }


    }


    fun loginWithFirebase(email: String, password: String) {
        setInProgress(true)
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email,
            password
        ).addOnSuccessListener {
            UiUtil.showToast(this, "Login successfully")
            setInProgress(false)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }.addOnFailureListener {
            UiUtil.showToast(applicationContext, it.localizedMessage ?: "Something went wrong")
            setInProgress(false)
        }


    }


}

