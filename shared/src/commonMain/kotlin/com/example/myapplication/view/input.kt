package com.example.myapplication.view

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.myapplication.resources.Res
import com.example.myapplication.resources.arrow_down
import com.example.myapplication.resources.purpose
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.MaxLength
import com.example.myapplication.theme.ShapeSize
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextFieldView(
    maxLength: Int,
    placeholder: String,
    onValueChange: (String) -> Unit,
    initialText: String = "",
    modifier: Modifier = Modifier,
    suffix: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    borderSelected: Color = AppColor.selected,
    borderNormal: Color = AppColor.border
) {
    var hasFocus by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = modifier
            .height(ShapeSize.inputField)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (hasFocus) borderSelected else borderNormal,
                shape = RoundedCornerShape(ShapeSize.cornerRadius)
            )
            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
        value = TextFieldValue(
            text = initialText,
            selection = TextRange(initialText.length),
        ),
        onValueChange = {
            if (it.text.length <= maxLength) {
                onValueChange(it.text)
            }
        },
        visualTransformation = visualTransformation,
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = keyboardType
        ),
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = initialText,
            innerTextField = innerTextField,
            enabled = true,
            singleLine = singleLine,
            placeholder = { TextSecondaryView(placeholder) },
            interactionSource = interactionSource,
            visualTransformation = VisualTransformation.None,
            suffix = suffix,
            contentPadding = ShapeSize.inputFieldContentPadding,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableTextFieldView(
    initialText: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    var text by remember(initialText) { mutableStateOf(TextFieldValue(text = initialText)) }
    val filteredOptions = options.filteredBy(text.text)

    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    val expanded = allowExpanded && filteredOptions.isNotEmpty()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = setExpanded,
    ) {
        DefaultTextFieldView(
            maxLength = MaxLength.purpose,
            placeholder = stringResource(Res.string.purpose),
            initialText = text.text,
            onValueChange = {
                text = TextFieldValue(
                    text = it,
                    selection = TextRange(it.length),
                )
                onValueChange(it)
            },
            suffix = {
                Icon(
                    imageVector = vectorResource(Res.drawable.arrow_down),
                    contentDescription = "",
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.SecondaryEditable)
                        .rotate(if (expanded) 180f else 0f),
                    tint = AppColor.arrowDown
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
        ) {
            filteredOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        text = TextFieldValue(
                            text = option.text,
                            selection = TextRange(option.text.length),
                        )
                        onValueChange(option.text)
                        setExpanded(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

private fun List<String>.filteredBy(text: String): List<AnnotatedString> {
    fun underlineSubsequence(needle: String, haystack: String): AnnotatedString? {
        return buildAnnotatedString {
            var i = 0
            for (char in needle) {
                val start = i
                haystack.indexOf(char, startIndex = i, ignoreCase = true).let {
                    if (it < 0) return null else i = it
                }
                append(haystack.substring(start, i))
                withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append(haystack[i])
                }
                i += 1
            }
            append(haystack.substring(i, haystack.length))
        }
    }
    return this.mapNotNull { option -> underlineSubsequence(text, option) }
}