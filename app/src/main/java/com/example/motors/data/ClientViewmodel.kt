package com.example.motors.data

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.motors.model.ClientModel
import com.example.motors.navigation.ROUTE_VIEW_CLIENT
import com.example.motors.network.ImgurService
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class ClientViewModel: ViewModel() {
    private val database = FirebaseDatabase.getInstance().reference.child("Clients")

    private fun getImgurService(): ImgurService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ImgurService::class.java)
    }

    private fun getFileFromUri(context: Context, uri: Uri):
            File? {
        return try {
            val inputStream = context.contentResolver
                .openInputStream(uri)
            val file = File.createTempFile("temp_image", ".jpg", context.cacheDir)
            file.outputStream().use { output ->
                inputStream?.copyTo(output)
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun uploadclientWithImage(
        uri: Uri,
        context: Context,
        name: String,
        gender: String,
        nationality: String,
        county: String,
        desc: String,
        navController: NavController



    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val file = getFileFromUri(context, uri)
                if (file == null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to process image", Toast.LENGTH_SHORT)
                            .show()
                    }
                    return@launch
                }

                val reqFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("image", file.name, reqFile)

                val response = getImgurService().uploadImage(
                    body,
                    "Client-ID 93f36ed8e919e39"
                )

                if (response.isSuccessful) {
                    val imageUrl = response.body()?.data?.link ?: ""

                    val clientId = database.push().key ?: ""
                    val client = ClientModel(
                        name, gender, nationality, county, desc, imageUrl, clientId
                    )

                    database.child(clientId).setValue(client)
                        .addOnCompleteListener {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        context,
                                        "Client saved successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate(ROUTE_VIEW_CLIENT)

                                }
                            }
                        }.addOnFailureListener {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        context,
                                        "Failed to save client",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Upload error", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Exception: ${e.localizedMessage}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    fun viewClients(
        client: MutableState<ClientModel>,
        clients: SnapshotStateList<ClientModel>,
        context: Context


    ): SnapshotStateList<ClientModel> {
        val ref = FirebaseDatabase.getInstance().getReference("Clients")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                clients.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(ClientModel::class.java)
                    value?.let {
                        clients.add(it)
                    }
                    if (clients.isNotEmpty()) {
                        client.value = clients.first()
                    } else {
                        Toast.makeText(context, "No clients found", Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    context,
                    "Failed to fetch clients: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()

            }


        })
        return clients
    }

    fun updateclient(
            context: Context,
            navController: NavController,
            name: String,
            gender: String,
            nationality: String,
            county: String,
            desc: String,
            clientId: String
        ) {
            val databaseReference = FirebaseDatabase.getInstance()
                .getReference("Clients/$clientId")
            val updatedClient = ClientModel(
                name, gender, nationality,
                county, desc, clientId
            )

            databaseReference.setValue(updatedClient)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(
                            context, (" Client Updated Successfully"),
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(ROUTE_VIEW_CLIENT)
                    } else {

                        Toast.makeText(context, "Client update failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
        fun deleteClient(context: Context, clientId: String)

         {
            AlertDialog.Builder(context)
                .setTitle("Delete Client")
                .setMessage("Are you sure you want to delete this client?")
                .setPositiveButton("Yes") { _, _ ->
                    val databaseReference = FirebaseDatabase.getInstance()
                        .getReference("Clients/$clientId")
                    databaseReference.removeValue().addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(
                                context,
                                "Client deleted Successfully",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(context, "Client not deleted", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }



