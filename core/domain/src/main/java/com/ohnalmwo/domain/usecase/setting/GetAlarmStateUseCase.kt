package com.ohnalmwo.domain.usecase.setting

import com.ohnalmwo.domain.repository.SettingRepository
import com.ohnalmwo.model.enum.Switch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlarmStateUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) {
    operator fun invoke(): Flow<Switch> =
        settingRepository.getAlarmState()
}