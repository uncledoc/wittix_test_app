package com.example.myapplication.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object TextSize {
    val xSmall = 9.sp
    val small = 10.sp
    val normal = 12.sp
    val large = 14.sp
}

object ShapeSize {
    val icon = 24.dp

    val bottomBarHeight = 56.dp

    val inputField = 48.dp
    val inputFieldContentPadding = PaddingValues(
        horizontal = 16.dp,
        vertical = 12.dp
    )
    val inputFieldDropDownDivideHeight =
        inputField - inputFieldContentPadding.calculateTopPadding() * 2

    val floatingButton = 52.dp
    val cornerRadius = 8.dp
    val checkbox = 20.dp // 18 in figma
    val checkboxBorder = 2.dp
}

object MaxLength {
    val amount = 15
    val purpose = 160
    val purposePayment = 360
}