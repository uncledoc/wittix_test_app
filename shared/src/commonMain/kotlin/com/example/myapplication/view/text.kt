package com.example.myapplication.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.TextSize

@Composable
fun TextPrimaryView(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = AppColor.textDefault,
    textAlign: TextAlign? = null,
) {
    TextPrimaryView(
        AnnotatedString(text = text),
        modifier = modifier,
        fontSize = fontSize,
        style = style,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun TextPrimaryView(
    annotatedString: AnnotatedString,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = AppColor.textDefault,
    textAlign: TextAlign? = null,
) {
    Text(
        annotatedString,
        modifier = modifier,
        style = style.copy(fontSize = fontSize, color = color),
        textAlign = textAlign
    )
}

@Composable
fun TextSecondaryView(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
) {
    TextSecondaryView(
        AnnotatedString(text = text),
        modifier = modifier,
        fontSize = fontSize,
        textAlign = textAlign
    )
}

@Composable
fun TextSecondaryView(
    annotatedString: AnnotatedString,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
) {
    TextPrimaryView(
        annotatedString,
        modifier = modifier,
        fontSize = fontSize,
        color = AppColor.textSecondary,
        textAlign = textAlign
    )
}

@Composable
fun TextTertiaryView(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextSize.small,
    textAlign: TextAlign? = null,
) {
    TextTertiaryView(
        AnnotatedString(text = text),
        modifier = modifier,
        fontSize = fontSize,
        textAlign = textAlign
    )
}

@Composable
fun TextTertiaryView(
    annotatedString: AnnotatedString,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextSize.small,
    textAlign: TextAlign? = null,
) {
    TextPrimaryView(
        annotatedString,
        modifier = modifier,
        fontSize = fontSize,
        color = AppColor.textTertiary,
        textAlign = textAlign
    )
}