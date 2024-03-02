package com.ilyes.amphibians

import android.app.Application
import com.ilyes.amphibians.data.AppContainer
import com.ilyes.amphibians.data.DefaultAppContainer

class AmphibiansPhotosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}