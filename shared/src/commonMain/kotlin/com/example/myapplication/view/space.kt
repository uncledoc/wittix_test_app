package com.example.myapplication.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.myapplication.theme.Paddings

@Composable
fun SpaceVerticalNormalView() {
    SpaceVerticalView(Paddings.normal)
}

@Composable
fun SpaceVerticalSmallView() {
    SpaceVerticalView(Paddings.small)
}

@Composable
fun SpaceVerticalXSmallView() {
    SpaceVerticalView(Paddings.xSmall)
}

@Composable
private fun SpaceVerticalView(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun SpaceHorizontalNormalView() {
    SpaceHorizontalView(Paddings.normal)
}

@Composable
fun SpaceHorizontalSmallView() {
    SpaceHorizontalView(Paddings.small)
}

@Composable
fun SpaceHorizontalXSmallView() {
    SpaceHorizontalView(Paddings.xSmall)
}

@Composable
private fun SpaceHorizontalView(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}