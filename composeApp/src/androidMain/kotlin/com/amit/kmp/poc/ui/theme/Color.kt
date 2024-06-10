package com.amit.kmp.poc.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

private val white = Color(0xffffffff)
private val blackForText = Color(0xff000000)

internal val alternateDarkColors = darkColorScheme(
    primary = Color(0xFF5265FF),
    background = Color(0xFF131629),
    onBackground = Color(0xFFFFFFFF),
//    background = Color(0xFF181A20),
    error = Color(0xFFE21221),
    surface = Color(0xFF181A20),
)

//
// val LightColors = lightColors(
//    primary = primaryGreen,
//    primaryVariant = primaryVariant,
//    secondary = blueForLinks,
//    background = white,
//    onBackground = blackForText,
//    onSurface = blackForText
// )
