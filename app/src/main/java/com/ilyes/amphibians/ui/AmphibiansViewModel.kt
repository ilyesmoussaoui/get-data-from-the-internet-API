package com.ilyes.amphibians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ilyes.amphibians.AmphibiansPhotosApplication
import com.ilyes.amphibians.data.AmphibiansPhotosRepository
import com.ilyes.amphibians.ui.model.AmphibiansPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val photos: List<AmphibiansPhoto>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}
class AmphibiansViewModel(private val amphibiansPhotosRepository: AmphibiansPhotosRepository) : ViewModel() {
    var amphibians: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
    private val _expandedState = MutableStateFlow(false)
    val expandedState: StateFlow<Boolean> = _expandedState
    init {
        getAmphibiansPhotos()
    }
    fun getAmphibiansPhotos() {
        viewModelScope.launch {
            amphibians = AmphibiansUiState.Loading
            amphibians = try {
                AmphibiansUiState.Success(amphibiansPhotosRepository.getAmphibiansPhotos())
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }
    fun toggleExpandedState() {
        _expandedState.value = !_expandedState.value
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibiansPhotosApplication)
                val amphibiansPhotosRepository = application.container.amphibiansPhotosRepository
                AmphibiansViewModel(amphibiansPhotosRepository = amphibiansPhotosRepository)
            }
        }
    }
}