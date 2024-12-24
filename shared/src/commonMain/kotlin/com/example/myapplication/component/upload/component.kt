package com.example.myapplication.component.upload

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.resources.Res
import com.example.myapplication.resources.doc_upload
import com.example.myapplication.resources.doc_upload_allowed
import com.example.myapplication.resources.upload
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.Paddings
import com.example.myapplication.view.SpaceVerticalSmallView
import com.example.myapplication.view.TextPrimaryView
import com.example.myapplication.view.TextTertiaryView
import io.github.vinceglb.filekit.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.core.PickerMode
import io.github.vinceglb.filekit.core.PickerType
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UploadDocumentComponent(
    modifier: Modifier = Modifier,
    viewModel: UploadDocumentComponentViewModel = koinViewModel()
) {
    val launcher = rememberFilePickerLauncher(
        type = PickerType.File(
            extensions = listOf("jpeg", "jpg", "png", "pdf", "doc", "docx")
        ),
        mode = PickerMode.Multiple(),
        title = stringResource(Res.string.doc_upload),
        onResult = viewModel::onFilePicked
    )

    Card(
        modifier = modifier.clickable { launcher.launch() },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(width = 1.dp, color = AppColor.borderGreen)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(Paddings.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = vectorResource(Res.drawable.upload),
                contentDescription = stringResource(Res.string.doc_upload),
            )
            SpaceVerticalSmallView()
            TextPrimaryView(
                stringResource(Res.string.doc_upload),
                style = MaterialTheme.typography.bodyLarge
            )
            TextTertiaryView(stringResource(Res.string.doc_upload_allowed))
        }
    }
}