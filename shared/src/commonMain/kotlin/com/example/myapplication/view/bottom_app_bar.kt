package com.example.myapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.myapplication.resources.Res
import com.example.myapplication.resources.menu_cards
import com.example.myapplication.resources.menu_dashboard
import com.example.myapplication.resources.menu_settings
import com.example.myapplication.resources.menu_statement
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.Paddings
import com.example.myapplication.theme.ShapeSize
import com.example.myapplication.theme.TextSize
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BottomAppBarView() {
    // it's hard to create without explanation
    val bottomShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height

        moveTo(0f, 0f)
        lineTo(width * 0.3f, 0f)
        cubicTo(
            width * 0.4f, 0f,
            width * 0.475f, height * 0.7f,
            width * 0.5f, height * 0.65f
        )
        cubicTo(
            width * 0.525f, height * 0.65f,
            width * 0.6f, 0f,
            width * 0.7f, 0f
        )
        lineTo(width, 0f)
        lineTo(width, height)
        lineTo(0f, height)
    }
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(
                WindowInsets.safeContent.only(WindowInsetsSides.Bottom)
                    .asPaddingValues()
            )
            .clip(bottomShape)
            .shadow(3.dp, bottomShape)
            .padding(top = 5.dp)
            .clip(bottomShape)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Items()
    }
}

@Composable
private fun Items() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(Paddings.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Item(
            title = stringResource(Res.string.menu_settings),
            image = vectorResource(Res.drawable.menu_settings)
        )
        Spacer(Modifier.width(Paddings.small))
        Item(
            title = stringResource(Res.string.menu_cards),
            image = vectorResource(Res.drawable.menu_cards)
        )
        Spacer(Modifier.weight(1f))
        Item(
            title = stringResource(Res.string.menu_statement),
            image = vectorResource(Res.drawable.menu_statement)
        )
        Spacer(Modifier.width(Paddings.small))
        Item(
            title = stringResource(Res.string.menu_dashboard),
            image = vectorResource(Res.drawable.menu_dashboard),
            isSelected = true
        )
    }
}

@Composable
private fun Item(
    title: String,
    image: ImageVector,
    isSelected: Boolean = false
) {
    val color = remember(isSelected) {
        if (isSelected) AppColor.menuSelected else AppColor.menuNormal
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(ShapeSize.bottomBarHeight)
    ) {
        Icon(
            modifier = Modifier.size(ShapeSize.icon),
            imageVector = image,
            contentDescription = title,
            tint = color,
        )
        SpaceVerticalSmallView()
        TextPrimaryView(
            text = title,
            fontSize = TextSize.xSmall,
            color = color,
        )
    }
}