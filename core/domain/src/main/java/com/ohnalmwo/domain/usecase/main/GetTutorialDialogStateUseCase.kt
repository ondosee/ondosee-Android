package com.ohnalmwo.domain.usecase.main

import com.ohnalmwo.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTutorialDialogStateUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke(): Flow<Boolean> =
        mainRepository.getTutorialDialogState()
}