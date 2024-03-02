package com.ilyes.amphibians.ui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class AmphibiansPhoto(
    val name: String,
    val type: String,
    val description: String,
    @SerialName("img_src") val imgSrc: String
)
