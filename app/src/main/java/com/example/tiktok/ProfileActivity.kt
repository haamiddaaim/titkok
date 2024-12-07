package com.example.tiktok

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tiktok.adapter.ProfileVideoAdapter
import com.example.tiktok.databinding.ActivityProfileBinding
import com.example.tiktok.model.UserModel
import com.example.tiktok.model.VideoModel
import com.example.tiktok.util.UiUtil
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import org.json.JSONException


class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var profileUserId: String
    lateinit var currentUserId: String
    lateinit var photoLauncher: ActivityResultLauncher<Intent>
    lateinit var profileUserModel: UserModel
    lateinit var adapter: ProfileVideoAdapter
    lateinit var mAuth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var swiperefreshlayout: SwipeRefreshLayout

    // lateinit var accessToken : AccessToken
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        profileUserId = intent.getStringExtra("profile_user_id")!!
        currentUserId = FirebaseAuth.getInstance().currentUser?.uid!!


        val accessToken = AccessToken.getCurrentAccessToken()

        val request = GraphRequest.newMeRequest(
            accessToken
        ) { `object`, response ->
            try {
                val fullname = `object`?.getString("name")
                val url = `object`?.getJSONObject("picture")?.getString("url")
                binding.profileUsername.text = fullname
                Picasso.get().load(url).into(binding.profilePic)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }


        val parameters = Bundle().apply {
            putString("fields", "id,name,link,picture.type(large)")
        }

        request.parameters = parameters
        request.executeAsync()


        /* accessToken.token
         val request = GraphRequest.newMeRequest(
             accessToken,
             object : GraphRequest.GraphJSONObjectCallback() {
                 override fun onCompleted(
                     `object`: JSONObject?,
                     response: GraphResponse?
                 ) {
                     `object`?.getString("name")
                     // Application code
                 }
             })
         val parameters = Bundle()
         parameters.putString("fields", "id,name,link")
         request.parameters = parameters
         request.executeAsync()*/

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        photoLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    //upload photo
                    uploadToFirestore(result.data?.data!!)
                }

            }

        if (profileUserId == currentUserId) {
            binding.profileBtn.text = "Logout"
            binding.profileBtn.setOnClickListener {
                logout()
            }
            binding.profilePic.setOnClickListener {
                checkPermissionAndPickPhoto()
            }
        } else {
            //other user profile
            binding.profileBtn.text = "Follow"
            binding.profileBtn.setOnClickListener {
                followUnfollowUser()
            }
        }
        getProfileDataFromFirebase()
        setupRecyclerView()
    }


    fun followUnfollowUser() {
        Firebase.firestore.collection("users")
            .document(currentUserId)
            .get()
            .addOnSuccessListener {
                val currentUserModel = it.toObject(UserModel::class.java)!!
                if (profileUserModel.followerList.contains(currentUserId)) {
                    //unfollow user
                    profileUserModel.followerList.remove(currentUserId)
                    currentUserModel.followingList.remove(profileUserId)
                    binding.profileBtn.text = "Follow"
                } else {
                    //follow user
                    profileUserModel.followerList.add(currentUserId)
                    currentUserModel.followingList.add(profileUserId)
                    binding.profileBtn.text = "Unfollow"
                }
                updateUserData(profileUserModel)
                updateUserData(currentUserModel)
            }
    }

    fun updateUserData(model: UserModel) {
        Firebase.firestore.collection("users")
            .document(model.id)
            .set(model)
            .addOnSuccessListener {
                getProfileDataFromFirebase()
            }
    }

    fun uploadToFirestore(photoUri: Uri) {
        binding.progressBar.visibility = View.VISIBLE
        val photoRef = FirebaseStorage.getInstance()
            .reference
            .child("profilePic/" + currentUserId)
        photoRef.putFile(photoUri)
            .addOnSuccessListener {
                photoRef.downloadUrl.addOnSuccessListener { downloadUrl ->

                    //video model store in firebase firestore
                    postToFirestore(downloadUrl.toString())
                }

            }
    }

    private fun postToFirestore(url: String) {
        Firebase.firestore.collection("users")
            .document(currentUserId)
            .update("profilePic", url)
            .addOnSuccessListener {
                getProfileDataFromFirebase()
            }
    }

    fun checkPermissionAndPickPhoto() {
        var readExternalPhoto: String = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            readExternalPhoto = android.Manifest.permission.READ_MEDIA_IMAGES
        } else {
            readExternalPhoto = android.Manifest.permission.READ_EXTERNAL_STORAGE
        }
        if (ContextCompat.checkSelfPermission(
                this,
                readExternalPhoto
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //we have permission
            openPhotoPicker()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(readExternalPhoto),
                100
            )
        }
    }

    private fun openPhotoPicker() {
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        photoLauncher.launch(intent)
    }

    private fun swipeToRefresh() {
        swiperefreshlayout.setOnRefreshListener {
            swiperefreshlayout.isRefreshing = false
            resetRecyclerview()
        }
    }

    private fun resetRecyclerview() {
        UiUtil.showToast(this, "Refresh...")


    }

    fun logout() {

        mAuth.signOut()
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                LoginManager.getInstance().logOut()
                val intent = Intent(this, LoginActivity::class.java)
                //open a new task or clear a task after logging out
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
    }

    fun getProfileDataFromFirebase() {
        Firebase.firestore.collection("users")
            .document(profileUserId)
            .get()
            .addOnSuccessListener {
                profileUserModel = it.toObject(UserModel::class.java)!!
                setUI()
            }
    }

    fun setUI() {
        profileUserModel.apply {
            Glide.with(binding.profilePic).load(profilePic)
                .apply(RequestOptions().placeholder(R.drawable.icon_account_circle))
                .circleCrop()
                .into(binding.profilePic)
            binding.profileUsername.text = "@" + username
            if (profileUserModel.followerList.contains(currentUserId))
                binding.profileBtn.text = "Unfollow"
            binding.progressBar.visibility = View.INVISIBLE
            binding.followingCount.text = followingList.size.toString()
            binding.followerCount.text = followerList.size.toString()
            Firebase.firestore.collection("videos")
                .whereEqualTo("uploaderId", profileUserId)
                .get().addOnSuccessListener {
                    binding.postCount.text = it.size().toString()
                }
        }

    }

    fun setupRecyclerView() {
        val options = FirestoreRecyclerOptions.Builder<VideoModel>()
            .setQuery(
                Firebase.firestore.collection("videos")
                    .whereEqualTo("uploaderId", profileUserId)
                    .orderBy("createdTime", Query.Direction.DESCENDING),
                VideoModel::class.java
            ).build()
        adapter = ProfileVideoAdapter(options)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.stopListening()
    }
}