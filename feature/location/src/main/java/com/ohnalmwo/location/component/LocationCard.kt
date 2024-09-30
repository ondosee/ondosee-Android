package com.ohnalmwo.location.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.component.lottie.AnimatedLottie
import com.ohnalmwo.design_system.icons.CurrentLocationIcon
import com.ohnalmwo.design_system.icons.HamburgerIcon
import com.ohnalmwo.design_system.icons.SettingDunghillIcon
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography
import com.ohnalmwo.model.enum.BackgroundType
import com.ohnalmwo.ui.getBackgroundColors

@Composable
fun LocationCard(
    modifier: Modifier = Modifier,
    location: String,
    significant: String,
    isCurrentLocation: Boolean,
    isExtension: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = getBackgroundColors(type = BackgroundType.RAIN)
                )
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isExtension) {
            Column(
                modifier = Modifier.height(112.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = location,
                        style = typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = colors.WHITE
                    )
                    Text(
                        text = significant,
                        style = typography.textLarge,
                        fontWeight = FontWeight.Medium,
                        color = colors.WHITE.copy(.75f)
                    )
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
            AnimatedLottie(
                modifier = Modifier.size(100.dp),
                rawId = com.ohnalmwo.design_system.R.raw.rainy
            )
        } else {
            Column {
                Text(
                    text = location,
                    style = typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = colors.WHITE
                )
                Text(
                    text = significant,
                    style = typography.textLarge,
                    fontWeight = FontWeight.Medium,
                    color = colors.WHITE.copy(.75f)
                )
            }
        }
    }
}

@Composable
fun EditableLocationCard(
    modifier: Modifier = Modifier,
    location: String,
    significant: String,
    isCurrentLocation: Boolean,
    isExtension: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HamburgerIcon(tint = colors.TERTIARY)
        LocationCard(
            modifier = Modifier.weight(1f),
            location = location,
            significant = significant,
            isCurrentLocation = isCurrentLocation,
            isExtension = isExtension
        )
        Box(
            modifier = Modifier
                .size(40.dp, 80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = colors.WARNING)
                .clickable { onClick() }
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            SettingDunghillIcon(tint = colors.WHITE)
        }
    }
}