package com.ohnalmwo.domain.usecase.main

import com.ohnalmwo.domain.repository.MainRepository
import javax.inject.Inject

class SetTutorialDialogStateUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(openDialog: Boolean) {
        mainRepository.setTutorialDialogState(openDialog = openDialog)
    }
}