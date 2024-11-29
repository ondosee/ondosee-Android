package com.ohnalmwo.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.component.shimmer.ShimmerBox
import com.ohnalmwo.design_system.component.topbar.LoadingOndoseeTopBar
import com.ohnalmwo.design_system.icons.MenuIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier,
    hazeState: HazeState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = colors.BACKGROUND_LOADING))
            .haze(state = hazeState)
            .statusBarsPadding()
            .padding(start = 20.dp, end = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoadingOndoseeTopBar { MenuIcon() }
        ShimmerBox(Modifier.padding(top = 40.dp, end = 15.dp).size(165.dp, 80.dp))
        ShimmerBox(Modifier.padding(top = 200.dp, end = 15.dp).size(200.dp, 34.dp))
        ShimmerBox(Modifier.padding(top = 6.dp, end = 15.dp).size(140.dp, 16.dp))
        LoadingWeatherForecastCard(Modifier.padding(top = 42.dp, end = 15.dp))
        LoadingWeatherForecastCard(Modifier.padding(top = 16.dp, end = 15.dp))
    }
}

@Composable
private fun LoadingWeatherForecastCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = colors.WHITE.copy(alpha = .2f))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "시간 별 일기예보",
            style = typography.textSmall,
            fontWeight = FontWeight.Bold,
            color = colors.WHITE.copy(.75f)
        )
        ShimmerBox(Modifier.fillMaxWidth(.8f).height(16.dp))
        ShimmerBox(Modifier.fillMaxWidth(.5f).height(16.dp))
    }
}