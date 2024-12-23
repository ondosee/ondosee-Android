package com.ohnalmwo.datastore.datasource.main

import androidx.datastore.core.DataStore
import com.ohnalmwo.datastore.Tutorial
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val tutorial: DataStore<Tutorial>
) : MainDataSource {
    override fun getTutorialDialogState(): Flow<Boolean> =
        tutorial.data.map { it.openDialog }

    override suspend fun setTutorialDialogState(openDialog: Boolean) {
        tutorial.updateData {
            it.toBuilder()
                .setOpenDialog(openDialog)
                .build()
        }
    }
}