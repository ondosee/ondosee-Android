package com.ohnalmwo.datastore.datasource.main

import kotlinx.coroutines.flow.Flow

interface MainDataSource {
    fun getTutorialDialogState(): Flow<Boolean>

    suspend fun setTutorialDialogState(openDialog: Boolean)
}