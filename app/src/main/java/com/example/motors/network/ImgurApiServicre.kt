package com.example.motors.network

import com.example.motors.model.ImgurResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Part

interface ImgurService {
    @Multipart
    @POST("3/image")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Header("Authorization") authorization: String
    ): Response<ImgurResponse>





}