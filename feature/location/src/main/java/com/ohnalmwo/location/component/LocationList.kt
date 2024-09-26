package com.ohnalmwo.location.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.ohnalmwo.design_system.theme.OndoseeTheme.colors
import com.ohnalmwo.design_system.theme.OndoseeTheme.typography

@Composable
fun LocationList(
    modifier: Modifier = Modifier,
    searchQuery: String,
    locations: List<String>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = locations,
            key = { data ->
                data
            }
        ) {
            LocationListItem(
                searchQuery = searchQuery,
                location = it,
            ) { location ->
                onClick(location)
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = colors.WHITE.copy(.15f)
            )
        }
    }
}

@Composable
fun LocationListItem(
    modifier: Modifier = Modifier,
    searchQuery: String,
    location: String,
    onClick: (String) -> Unit
) {
    val highlightText = buildAnnotatedString {
        withStyle(style = SpanStyle(colors.SECONDARY)) {
            append(location)
        }
        searchQuery.toRegex().findAll(location).forEach { result ->
            addStyle(
                style = SpanStyle(color = colors.PRIMARY),
                start = result.range.first,
                end = result.range.last + 1
            )
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clickable { onClick(location) }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = highlightText,
            style = typography.textLarge,
            fontWeight = FontWeight.Normal,
        )
    }
}