package com.ilyes.amphibians.data

import com.ilyes.amphibians.network.AmphibiansApiService
import com.ilyes.amphibians.ui.model.AmphibiansPhoto

interface AmphibiansPhotosRepository {
    suspend fun getAmphibiansPhotos(): List<AmphibiansPhoto>

}
class NetworkAmphibiansPhotosRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansPhotosRepository {
    override suspend fun getAmphibiansPhotos(): List<AmphibiansPhoto> = amphibiansApiService.getAmphibians()

}
