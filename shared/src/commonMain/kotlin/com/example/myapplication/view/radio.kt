package com.example.myapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.resources.Res
import com.example.myapplication.resources.express
import com.example.myapplication.resources.regular
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.Paddings
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransferRadioView(
    onValueChange: (Boolean) -> Unit,
    initialValue: Boolean = false,
) {
    var selected by remember(initialValue) { mutableStateOf(initialValue) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val modifier = Modifier.fillMaxWidth().weight(1f)

        Item(stringResource(Res.string.regular), selected, modifier.clickable {
            if (!selected) {
                selected = !selected
                onValueChange(selected)
            }
        })
        Spacer(Modifier.width(Paddings.small))
        Item(stringResource(Res.string.express), !selected, modifier.clickable {
            if (selected) {
                selected = !selected
                onValueChange(selected)
            }
        })
    }
}

@Composable
private fun Item(
    title: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val color = if (isSelected) AppColor.selected else AppColor.border
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(width = 1.dp, color = color)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(Paddings.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextPrimaryView(title)
        }
    }
}