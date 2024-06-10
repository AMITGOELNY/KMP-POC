package com.amit.kmp.poc.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(content: @Composable () -> Unit) {
//    val isSmallScreen = getScreenWidthDp() <= 360
//    val typography = if (isSmallScreen) SmallTypography else Typography
//    val dimensions = if (isSmallScreen) smallDimensions else sw360Dimensions

    ProvideDimens(dimensions = sw360Dimensions) {
        MaterialTheme(
            colorScheme = alternateDarkColors,
            shapes = Shapes,
//            typography = Typography,
            content = content,
        )
    }
}

private object AppTheme {
    // val colors: Colors
    //     @Composable
    //     get() = LocalAppColors.current

    val dimens: Dimensions
        @Composable get() = LocalAppDimens.current
}

val Dimens: Dimensions
    @Composable get() = AppTheme.dimens
