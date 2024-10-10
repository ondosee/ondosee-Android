package com.ohnalmwo.model.enum

enum class Significant {
    HEAT_WAVE, // 폭염
    COLD_WAVE, // 한파
    DROUGHT, // 건조
    SNOW, // 눈
    RAIN, // 비
    GALE, // 강풍

    BEST_10, // 미세먼지 최고
    GOOD_10, // 미세먼지 좋음
    FAIR_10, // 미세먼지 괜찮음
    AVERAGE_10, // 미세먼지 보통
    POOR_10, // 미세먼지 나쁨
    BAD_10, // 미세먼지 매우 나쁨
    WORST_10, // 미세먼지 최악

    BEST_25, // 초미세먼지 최고
    GOOD_25, // 초미세먼지 좋음
    FAIR_25, // 초미세먼지 괜찮음
    AVERAGE_25, // 초미세먼지 보통
    POOR_25, // 초미세먼지 나쁨
    BAD_25, // 초미세먼지 매우 나쁨
    WORST_25 // 초미세먼지 최악
}