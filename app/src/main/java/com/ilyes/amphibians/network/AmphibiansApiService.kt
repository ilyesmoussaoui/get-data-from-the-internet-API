package com.ilyes.amphibians.network

import com.ilyes.amphibians.ui.model.AmphibiansPhoto
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibiansPhoto>
}