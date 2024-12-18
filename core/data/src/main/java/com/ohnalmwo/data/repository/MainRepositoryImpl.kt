package com.ohnalmwo.data.repository

import com.ohnalmwo.datastore.datasource.main.MainDataSource
import com.ohnalmwo.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localMainDataSource: MainDataSource
) : MainRepository {
    override fun getTutorialDialogState(): Flow<Boolean> =
        localMainDataSource.getTutorialDialogState()

    override suspend fun setTutorialDialogState(openDialog: Boolean) {
        localMainDataSource.setTutorialDialogState(openDialog = openDialog)
    }
}