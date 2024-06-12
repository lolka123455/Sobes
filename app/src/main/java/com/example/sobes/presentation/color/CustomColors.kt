package com.example.sobes.presentation.color

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class CustomColors(
    white: Color,
    mainBlack: Color,
    black: Color,
    brightBlue: Color,
    aquaBlue: Color,
    whiteTranslucent15: Color,
    whiteTranslucent60: Color,
    grey: Color,
    grey1: Color,
    grey2: Color,
    grey3: Color,
    grey4: Color,
    green: Color,
    lightRed: Color,
    darkBlue90: Color,
    label: Color,
    greyBlue: Color,
    darkGreyBlue: Color,
    lightGrey: Color,
    lightGreyBlue: Color,
    blueGrey: Color,
    blueGrey1: Color,
    darkBlue: Color,
    red: Color,
    yellow: Color,
    blueLink: Color,
    pink: Color,
    blackSemiTransparent: Color,
    isLight: Boolean,
    blueFootnotes: Color,
    buttonBackgroundGeneral: Color,
    blueDarkAppearance: Color,
    statusGeneral: Color,
    greenDarkAppearance: Color,
    yellowDarkAppearance: Color,
    redDarkAppearance: Color,
    backgroundDarkAppearance: Color,
    labelColorBackground: Color,
    gray1: Color,
    gray2DarkAppearance: Color,
) {
    var labelColorBackground by mutableStateOf(labelColorBackground)
        private set

    var greenDarkAppearance by mutableStateOf(greenDarkAppearance)
        private set

    var yellowDarkAppearance by mutableStateOf(yellowDarkAppearance)
        private set

    var redDarkAppearance by mutableStateOf(redDarkAppearance)
        private set

    var statusGeneral by mutableStateOf(statusGeneral)
        private set

    var white by mutableStateOf(white)
        private set

    var mainBlack by mutableStateOf(mainBlack)
        private set

    var grey by mutableStateOf(grey)
        private set
    var darkBlue90 by mutableStateOf(darkBlue90)
        private set

    var grey1 by mutableStateOf(grey1)
        private set

    var brightBlue by mutableStateOf(brightBlue)
        private set

    var aquaBlue by mutableStateOf(aquaBlue)
        private set

    var whiteTranslucent15 by mutableStateOf(whiteTranslucent15)
        private set

    var whiteTranslucent60 by mutableStateOf(whiteTranslucent60)
        private set

    var grey2 by mutableStateOf(grey2)
        private set

    var grey3 by mutableStateOf(grey3)
        private set

    var grey4 by mutableStateOf(grey4)
        private set

    var green by mutableStateOf(green)
        private set

    var lightRed by mutableStateOf(lightRed)
        private set

    var label by mutableStateOf(label)
        private set

    var greyBlueColor by mutableStateOf(greyBlue)
        private set

    var lightGrey by mutableStateOf(lightGrey)
        private set

    var lightGreyBlue by mutableStateOf(lightGreyBlue)
        private set

    var blueGrey by mutableStateOf(blueGrey)
        private set

    var blueFootnotes by mutableStateOf(blueFootnotes)
        private set

    var blueGrey1 by mutableStateOf(blueGrey1)
        private set

    var darkBlue by mutableStateOf(darkBlue)
        private set

    var darkGreyBlue by mutableStateOf(darkGreyBlue)
        private set

    var red by mutableStateOf(red)
        private set

    var yellow by mutableStateOf(yellow)
        private set

    var black by mutableStateOf(black)
        private set

    var blueLink by mutableStateOf(blueLink)
        private set

    var pink by mutableStateOf(pink)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    var blackSemiTransparent by mutableStateOf(blackSemiTransparent)
        private set

    var buttonBackgroundGeneral by mutableStateOf(buttonBackgroundGeneral)
        private set

    var blueDarkAppearance by mutableStateOf(blueDarkAppearance)
        private set

    var backgroundDarkAppearance by mutableStateOf(backgroundDarkAppearance)
        private set

    var gray1 by mutableStateOf(gray1)
        private set

    var gray2DarkAppearance by mutableStateOf(gray2DarkAppearance)
        private set

    fun copy(
        primary: Color = this.white,
        text: Color = this.mainBlack,
        background: Color = this.black,
        grey: Color = this.grey,
        darkBlue90: Color = this.darkBlue90,
        grey1: Color = this.grey1,
        brightBlue: Color = this.brightBlue,
        aquaBlue: Color = this.aquaBlue,
        whiteTranslucent15: Color = this.whiteTranslucent15,
        whiteTranslucent60: Color = this.whiteTranslucent60,
        grey2: Color = this.grey2,
        grey3: Color = this.grey3,
        grey4: Color = this.grey4,
        green: Color = this.grey4,
        lightRed: Color = this.lightRed,
        labelColor: Color = this.label,
        greyBlue: Color = this.greyBlueColor,
        darkGreyBlue: Color = this.darkGreyBlue,
        lightGrey: Color = this.lightGrey,
        lightGreyBlue: Color = this.lightGreyBlue,
        blueGrey: Color = this.blueGrey,
        blueGrey1: Color = this.blueGrey1,
        darkBlue: Color = this.darkBlue,
        error: Color = this.red,
        yellow: Color = this.yellow,
        blueLink: Color = this.blueLink,
        pink: Color = this.pink,
        isLight: Boolean = this.isLight,
        blueFootnotes: Color = this.blueFootnotes,
        buttonBackgroundGeneral: Color = this.buttonBackgroundGeneral,
        blueDarkAppearance: Color = this.blueDarkAppearance,
        statusGeneral: Color = this.statusGeneral,
        backgroundDarkAppearance: Color = this.backgroundDarkAppearance,
        labelColorBackground: Color = this.labelColorBackground,
        gray1: Color = this.gray1,
        gray2DarkAppearance: Color = this.gray2DarkAppearance,
    ) = CustomColors(
        white = primary,
        mainBlack = text,
        black = background,
        grey = grey,
        darkBlue90 = darkBlue90,
        grey1 = grey1,
        brightBlue = brightBlue,
        aquaBlue = aquaBlue,
        whiteTranslucent15 = whiteTranslucent15,
        whiteTranslucent60 = whiteTranslucent60,
        grey2 = grey2,
        grey3 = grey3,
        grey4 = grey4,
        green = green,
        lightRed = lightRed,
        label = labelColor,
        greyBlue = greyBlue,
        lightGrey = lightGrey,
        lightGreyBlue = lightGreyBlue,
        blueGrey = blueGrey,
        blueGrey1 = blueGrey1,
        darkBlue = darkBlue,
        darkGreyBlue = darkGreyBlue,
        red = error,
        yellow = yellow,
        blueLink = blueLink,
        pink = pink,
        blackSemiTransparent = blackSemiTransparent,
        isLight = isLight,
        blueFootnotes = blueFootnotes,
        buttonBackgroundGeneral = buttonBackgroundGeneral,
        blueDarkAppearance = blueDarkAppearance,
        statusGeneral = statusGeneral,
        greenDarkAppearance = greenDarkAppearance,
        yellowDarkAppearance = yellowDarkAppearance,
        redDarkAppearance = redDarkAppearance,
        backgroundDarkAppearance = backgroundDarkAppearance,
        labelColorBackground = labelColorBackground,
        gray1 = gray1,
        gray2DarkAppearance = gray2DarkAppearance,
    )

    fun updateColorsFrom(other: CustomColors) {
        white = other.white
        mainBlack = other.mainBlack
        grey = other.grey
        grey1 = other.grey1
        darkBlue90 = other.darkBlue90
        brightBlue = other.brightBlue
        aquaBlue = other.aquaBlue
        whiteTranslucent15 = other.whiteTranslucent15
        whiteTranslucent60 = other.whiteTranslucent60
        grey2 = other.grey2
        grey3 = other.grey3
        grey4 = other.grey4
        lightGrey = other.lightGrey
        lightGreyBlue = other.lightGreyBlue
        blueGrey = other.blueGrey
        darkGreyBlue = other.darkGreyBlue
        blueGrey1 = other.blueGrey1
        black = other.black
        darkBlue = other.darkBlue
        red = other.red
        lightRed = other.lightRed
        green = other.green
        yellow = other.yellow
        pink = other.pink
        isLight = other.isLight
        blackSemiTransparent = other.blackSemiTransparent
        blueFootnotes = other.blueFootnotes
        buttonBackgroundGeneral = other.buttonBackgroundGeneral
        blueDarkAppearance = other.blueDarkAppearance
        statusGeneral = other.statusGeneral
        greenDarkAppearance = other.greenDarkAppearance
        redDarkAppearance = other.redDarkAppearance
        yellowDarkAppearance = other.yellowDarkAppearance
        backgroundDarkAppearance = other.backgroundDarkAppearance
        labelColorBackground = other.labelColorBackground
        gray1 = other.grey1
        gray2DarkAppearance = other.gray2DarkAppearance
    }
}

val LocalColors = staticCompositionLocalOf { darkColors() }