package com.example.sobes.presentation.navigation.core

import androidx.annotation.DrawableRes
import com.example.sobes.R

object SobesIcons {
    val BaseScreenIconSelected = R.drawable.ic_tab_base_selected
    val BaseScreenIconUnselected = R.drawable.ic_tab_base_unselected
    val SearchScreenIconSelected = R.drawable.ic_tab_search_selected
    val SearchScreenIconUnselected = R.drawable.ic_tab_search_unselected
}

sealed class Icon {
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}