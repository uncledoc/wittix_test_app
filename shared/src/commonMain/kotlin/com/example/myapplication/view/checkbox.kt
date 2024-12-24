package com.example.myapplication.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import com.example.myapplication.resources.Res
import com.example.myapplication.resources.check
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.Paddings
import com.example.myapplication.theme.ShapeSize
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CheckboxView(text: String, isChecked: Boolean = true) {
    CheckboxView(buildAnnotatedString { append(text) }, isChecked)
}

@Composable
fun CheckboxView(text: AnnotatedString, isChecked: Boolean = true) {
    val (checkedState, onStateChange) = remember { mutableStateOf(isChecked) }
    Row(
        Modifier.fillMaxWidth()
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextSecondaryView(text, modifier = Modifier.weight(1f))
        Spacer(Modifier.width(Paddings.normal))
        Box(
            modifier = Modifier
                .size(ShapeSize.checkbox)
                .border(ShapeSize.checkboxBorder, AppColor.checkbox),
            contentAlignment = Alignment.Center
        ) {
            if (checkedState) {
                Icon(
                    imageVector = vectorResource(Res.drawable.check),
                    contentDescription = null,
                    tint = AppColor.checkbox,
                )
            }
        }
    }
}
