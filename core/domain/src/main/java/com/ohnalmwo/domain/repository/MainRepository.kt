package com.ohnalmwo.domain.repository

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getTutorialDialogState(): Flow<Boolean>

    suspend fun setTutorialDialogState(openDialog: Boolean)
}