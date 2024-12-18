package com.ohnalmwo.domain.usecase.setting

import com.ohnalmwo.domain.repository.SettingRepository
import javax.inject.Inject

class SetAlarmTimeUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) {
    suspend operator fun invoke(alarmTime: String) {
        settingRepository.setAlarmTime(alarmTime = alarmTime)
    }
}