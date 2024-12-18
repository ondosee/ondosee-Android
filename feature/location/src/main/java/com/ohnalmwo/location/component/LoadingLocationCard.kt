package com.ohnalmwo.location.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.component.shimmer.ShimmerBox
import com.ohnalmwo.design_system.icons.CurrentLocationIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.model.enum.Significant
import com.ohnalmwo.ui.getBackgroundColors

@Composable
fun LoadingLocationCard(
    modifier: Modifier = Modifier,
    isCurrentLocation: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Significant.WORST_10.getBackgroundColors()[0])
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ShimmerBox(Modifier.size(136.dp, 22.dp))
            ShimmerBox(Modifier.size(88.dp, 18.dp))
        }
        if (isCurrentLocation) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CurrentLocationIcon(
                    modifier = Modifier.size(16.dp),
                    tint = colors.WHITE
                )
                Text(
                    text = "현재 위치",
                    style = typography.textMedium,
                    fontWeight = FontWeight.Medium,
                    color = colors.WHITE
                )
            }
        }
    }
}