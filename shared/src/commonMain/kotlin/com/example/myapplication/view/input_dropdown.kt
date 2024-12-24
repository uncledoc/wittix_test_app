package com.example.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myapplication.resources.Res
import com.example.myapplication.resources.arrow_down
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.MaxLength
import com.example.myapplication.theme.ShapeSize
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class DropdownTextFieldViewItem(
    val icon: DrawableResource,
    val title: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownTextFieldView(
    initialText: String,
    initialItem: DropdownTextFieldViewItem,
    items: List<DropdownTextFieldViewItem>,
    onValueChange: (String) -> Unit,
    onItemChange: (DropdownTextFieldViewItem) -> Unit,
) {
    var item by remember(initialItem) { mutableStateOf(initialItem) }

    val (expanded, setExpanded) = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = setExpanded,
    ) {
        DefaultTextFieldView(
            maxLength = MaxLength.amount,
            placeholder = "",
            singleLine = true,
            initialText = initialText,
            keyboardType = KeyboardType.NumberPassword,
            onValueChange = { onValueChange(it) },
            suffix = {
                Row(
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.SecondaryEditable)
                        .width(93.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VerticalDivider(
                        modifier = Modifier.height(ShapeSize.inputFieldDropDownDivideHeight),
                        color = AppColor.border
                    )
                    SpaceHorizontalSmallView()
                    Image(
                        imageVector = vectorResource(item.icon),
                        contentDescription = item.title,
                    )
                    SpaceHorizontalSmallView()
                    TextPrimaryView(item.title)
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = vectorResource(Res.drawable.arrow_down),
                        contentDescription = "",
                        modifier = Modifier.rotate(if (expanded) 180f else 0f),
                        tint = AppColor.arrowDown
                    )
                }
            },
            borderNormal = AppColor.selected,
            borderSelected = AppColor.selected
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
        ) {
            items.forEach { option ->
                DropdownMenuItem(
                    leadingIcon = {
                        Image(
                            imageVector = vectorResource(option.icon),
                            contentDescription = option.title,
                        )
                    },
                    text = { Text(option.title, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        item = option
                        onItemChange(option)
                        setExpanded(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}