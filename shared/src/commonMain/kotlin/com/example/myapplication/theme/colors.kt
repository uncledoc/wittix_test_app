package com.example.myapplication.theme

import androidx.compose.ui.graphics.Color

object AppColor {
    val textDefault = Color(0XFF06060A)
    val textSecondary = Color(0xFF6C6B6B)
    val textTertiary = Color(0xFF4F5860)

    val border = Color(0xFFE3E6E9)
    val borderGreen = Color(0xFF34AA54)

    val checkbox = borderGreen
    val button = borderGreen
    val buttonTextColor = Color(0xFFF2FCFD)

    val selected = Color(0xFF2431E0)
    val arrowDown = Color(0xFF00CBD7)

    val gradientRadialBlue = arrayOf(
        0.0f to Color(0xFF2B48FF),
        0.3f to Color(0xFF2336E5),
        1.0f to Color(0xFF0B24BD),
    )

    val menuNormal = Color(0xFF4F5860)
    val menuSelected = Color(0xFF2E5BFF)
}