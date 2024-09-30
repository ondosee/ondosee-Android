package com.ohnalmwo.location

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.component.button.OndoseeBackButton
import com.ohnalmwo.design_system.component.textfield.SearchTextField
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.location.component.LocationList
import com.ohnalmwo.location.component.LocationText

@Composable
fun AddLocationScreen(
    navigateToBack: () -> Unit
) {
    var location by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.BACKGROUND)
            .statusBarsPadding()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OndoseeBackButton {
            navigateToBack()
        }
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            LocationText(text = "위치 추가하기")
            SearchTextField(
                modifier = Modifier.padding(top = 16.dp),
                placeHolder = "도시 또는 공항 검색",
                setText = location,
                singleLine = true
            ) {
                location = it
            }
            LocationList(
                searchQuery = location,
                locations = listOf("경기도 광주시", "광주광역시 동구", "광주광역시 남구", "광주광역시 광산구", "광주광역시 북구")
            ) {}
        }
    }
}