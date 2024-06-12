package com.example.sobes.presentation.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun darkColors() = CustomColors(
    white = Color(0xFFFFFFFF),
    mainBlack = Color(0xFF333333),
    black = Color(0xFF000000),
    grey = Color(0xFF999999),
    brightBlue = Color(0xFF0076ED),
    aquaBlue = Color(0xFFA9DBFF),
    whiteTranslucent15 = Color(0x26FFFFFF),
    whiteTranslucent60 = Color(0x99FFFFFF),
    grey1 = Color(0xFF666666),
    darkBlue90 = Color(0xE60B192AE5),
    grey2 = Color(0xFF999999),
    grey3 = Color(0xFFCCCCCC),
    grey4 = Color(0xFFF2F2F2),
    green = Color(0xFF7F7E68),
    label = Color(0xFF3C3C434D),
    greyBlue = Color(0xFFC4C9D8),
    darkGreyBlue = Color(0xFF8995AA),
    lightGrey = Color(0xFFF9F9F9),
    lightGreyBlue = Color(0xFFF0F1F3),
    blueGrey = Color(0xFFA8B5CB),
    blueGrey1 = Color(0xFF8995AA),
    darkBlue = Color(0xFF0B192A),
    red = Color(0xFFE01F1F),
    lightRed = Color(0xFFFF5252),
    yellow = Color(0xFFE59C00),
    blueLink = Color(0xFF007AFF),
    pink = Color(0xFFFFE1E1),
    isLight = true,
    blackSemiTransparent = Color(0x80000000),
    blueFootnotes = Color(0xFFA9D8FF),
    buttonBackgroundGeneral = Color(0xFF0076ED),
    blueDarkAppearance = Color(0xFF2793FF),
    statusGeneral = Color(0xFF002947),
    greenDarkAppearance = Color(0xFF78B100),
    yellowDarkAppearance = Color(0xFFE59C00),
    redDarkAppearance = Color(0xFFFF5252),
    backgroundDarkAppearance = Color(0xFF006432),
    labelColorBackground = Color(0xFF0B192A),
    gray1 = Color(0xFF1D3852),
    gray2DarkAppearance = Color(0xFF3E5060),
)

fun lightColors() = CustomColors(
    white = Color(0xFF002947),
    mainBlack = Color(0xFF333333),
    black = Color(0xFF000000),
    grey = Color(0xFF999999),
    brightBlue = Color(0xFF0076ED),
    aquaBlue = Color(0xFF44647D),
    whiteTranslucent15 = Color(0x2682B1E4),
    whiteTranslucent60 = Color(0x99004476),
    grey1 = Color(0xFF666666),
    grey2 = Color(0xFF999999),
    grey3 = Color(0xFFCCCCCC),
    grey4 = Color(0xFFF2F2F2),
    green = Color(0xFF78B100),
    darkBlue90 = Color(0xE60B192AE5),
    label = Color(0xFF3C3C434D),
    greyBlue = Color(0xFFC4C9D8),
    darkGreyBlue = Color(0xFF8995AA),
    lightGrey = Color(0xFFF9F9F9),
    lightGreyBlue = Color(0xFFF0F1F3),
    darkBlue = Color(0xFFDFE7F3),
    blueGrey = Color(0xFFA8B5CB),
    blueGrey1 = Color(0xFF8995AA),
    red = Color(0xFFE01F1F),
    lightRed = Color(0xFFFF5252),
    yellow = Color(0xFFE59C00),
    blueLink = Color(0xFF007AFF),
    pink = Color(0xFFFFE1E1),
    isLight = false,
    blackSemiTransparent = Color(0x80000000),
    blueFootnotes = Color(0xFF44647D),
    buttonBackgroundGeneral = Color(0xFF0076ED),
    blueDarkAppearance = Color(0xFF0A84FF),
    statusGeneral = Color(0xFFF5F8FF),
    greenDarkAppearance = Color(0xFF78B100),
    yellowDarkAppearance = Color(0xFFE59C00),
    redDarkAppearance = Color(0xFFFF5252),
    backgroundDarkAppearance = Color(0xFFFFFFFF),
    labelColorBackground = Color(0xFFDFE7F3),
    gray1 = Color(0xFFB8CBDC),
    gray2DarkAppearance = Color(0xFFFDFDFD),
)

@Composable
fun SobesTheme(
    typography: CustomTypography = SobesThemeObj.typography,
    colors: CustomColors = if (isSystemInDarkTheme()) {
        darkColors()
    } else {
//        lightColors()  //TODO убрать если хотим восстановить белую тему
        darkColors()
    },
    content: @Composable () -> Unit,
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography,
    ) {}

    MaterialTheme(
        typography = Typography(
            titleLarge = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                lineHeight = 28.sp
            ),
            headlineSmall = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 34.sp
            ),
        ),
    ) {
        content()
    }
}