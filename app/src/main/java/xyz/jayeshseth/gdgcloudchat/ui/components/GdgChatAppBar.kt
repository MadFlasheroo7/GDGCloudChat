@file:OptIn(ExperimentalMaterial3Api::class)
package xyz.jayeshseth.gdgcloudchat.ui.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import xyz.jayeshseth.gdgcloudchat.ui.theme.GDGCloudChatTheme

@Composable
fun GdgChatAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val backgroundColors = TopAppBarDefaults.centerAlignedTopAppBarColors()
    val backgroundColor = lerp(
        backgroundColors.containerColor(colorTransitionFraction = 0f).value,
        backgroundColors.containerColor(colorTransitionFraction = 1f).value,
        FastOutLinearInEasing.transform(scrollBehavior?.state?.overlappedFraction ?: 0f)
    )

    val foregroundColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent,
        scrolledContainerColor = Color.Transparent
    )
    Box(modifier = Modifier.background(backgroundColor)) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            actions = actions,
            title = title,
            scrollBehavior = scrollBehavior,
            colors = foregroundColors,
            navigationIcon = {}
        )
    }
}

@Preview
@Composable
fun GdgChatAppBarPreview() {
    GDGCloudChatTheme {
        GdgChatAppBar(title = { Text("Preview!") })
    }
}

@Preview
@Composable
fun JetchatAppBarPreviewDark() {
    GDGCloudChatTheme(darkTheme = true) {
        GdgChatAppBar(title = { Text("Preview!") })
    }
}