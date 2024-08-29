package com.ohnalmwo.ui

import com.ohnalmwo.design_system.R
import com.ohnalmwo.model.enum.Route

sealed class BottomBarItem(val title: String, val icon: Int, val route: Route) {
    data object Main : BottomBarItem("대시보드", R.drawable.ic_main, Route.Main)
    data object Weekly : BottomBarItem("주간예보", R.drawable.ic_weekly, Route.Weekly)
    data object Setting : BottomBarItem("설정", R.drawable.ic_setting, Route.Setting)
}