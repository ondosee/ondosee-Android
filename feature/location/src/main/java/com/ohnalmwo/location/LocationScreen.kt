package com.ohnalmwo.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.component.bottomsheet.OptionBottomSheet
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.location.component.LocationCard
import com.ohnalmwo.location.component.LocationCountText
import com.ohnalmwo.location.component.LocationText

@Composable
fun LocationScreen(
    navigateToLocationManagement: () -> Unit,
    navigateToAddLocation: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .statusBarsPadding()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            LocationText(text = "위치")
            LocationCountText(size = 4)
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(4) {
                LocationCard(
                    location = "광주광역시 광산구",
                    significant = "비 | 강수확률 90%",
                    isCurrentLocation = it == 0,
                    isExtension = true
                )
            }
        }
        OptionBottomSheet(
            closeSheet = {},
            navigateToLocationManagement = {
                navigateToLocationManagement()
            },
            navigateToAddLocation = {
                navigateToAddLocation()
            }
        )
    }
}