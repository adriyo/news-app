package com.adriyo.newsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.adriyo.newsapp.R

object AppFont {
    val Poppin = FontFamily(
        Font(R.font.poppins_regular),
        Font(R.font.poppins_italic, style = FontStyle.Italic),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_mediumitalic, FontWeight.Medium, style = FontStyle.Italic),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_bolditalic, FontWeight.Bold, style = FontStyle.Italic)
    )
}

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.Poppin),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.Poppin),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.Poppin),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.Poppin),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.Poppin),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.Poppin),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.Poppin),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.Poppin),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.Poppin),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.Poppin),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.Poppin),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.Poppin),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.Poppin),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.Poppin),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.Poppin)
)