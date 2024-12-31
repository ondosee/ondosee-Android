package com.ohnalmwo.domain.usecase.setting

import com.ohnalmwo.domain.repository.SettingRepository
import com.ohnalmwo.model.enum.Switch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlarmTimeUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) {
    operator fun invoke(): Flow<String> =
        settingRepository.getAlarmTime()
}