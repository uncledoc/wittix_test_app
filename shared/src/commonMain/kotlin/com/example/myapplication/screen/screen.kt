package com.example.myapplication.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.component.upload.UploadDocumentComponent
import com.example.myapplication.resources.Res
import com.example.myapplication.resources.agree
import com.example.myapplication.resources.available_balance
import com.example.myapplication.resources.back
import com.example.myapplication.resources.balance
import com.example.myapplication.resources.button_send
import com.example.myapplication.resources.doc
import com.example.myapplication.resources.doc_upload_desc
import com.example.myapplication.resources.options
import com.example.myapplication.resources.plus
import com.example.myapplication.resources.purpose_payment
import com.example.myapplication.resources.rate
import com.example.myapplication.resources.rate_desc
import com.example.myapplication.resources.read_more
import com.example.myapplication.resources.receive
import com.example.myapplication.resources.save
import com.example.myapplication.resources.send
import com.example.myapplication.resources.subtitle
import com.example.myapplication.resources.title
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.MaxLength
import com.example.myapplication.theme.Paddings
import com.example.myapplication.theme.ShapeSize
import com.example.myapplication.theme.TextSize
import com.example.myapplication.view.BottomAppBarView
import com.example.myapplication.view.CheckboxView
import com.example.myapplication.view.DefaultTextFieldView
import com.example.myapplication.view.DropdownTextFieldView
import com.example.myapplication.view.ExpandableTextFieldView
import com.example.myapplication.view.SpaceVerticalNormalView
import com.example.myapplication.view.SpaceVerticalSmallView
import com.example.myapplication.view.SpaceVerticalXSmallView
import com.example.myapplication.view.TextPrimaryView
import com.example.myapplication.view.TextSecondaryView
import com.example.myapplication.view.TextTertiaryView
import com.example.myapplication.view.TransferRadioView
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardScreenViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = vectorResource(Res.drawable.back),
                                contentDescription = stringResource(Res.string.back),
                            )
                        }
                    },
                    title = { Text(stringResource(Res.string.title)) },
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            IconButton(
                onClick = {},
                modifier = Modifier.offset(y = 43.dp)
                    .size(ShapeSize.floatingButton)
                    .background(
                        brush = Brush.radialGradient(*AppColor.gradientRadialBlue),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(Res.drawable.plus),
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        },
        bottomBar = { BottomAppBarView() }
    ) { innerPadding ->
        val uiState = viewModel.uiState.collectAsState()

        DashboardScreenSuccessBody(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding() / 2
                )
                .padding(horizontal = Paddings.normal)
                .verticalScroll(rememberScrollState()),
            bottomSpace = innerPadding.calculateBottomPadding() / 2,
            uiState = uiState.value,
            viewModel = viewModel,
        )
    }
}

@Composable
fun DashboardScreenSuccessBody(
    modifier: Modifier = Modifier,
    bottomSpace: Dp,
    uiState: DashboardScreenState,
    viewModel: DashboardScreenViewModel,
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextPrimaryView(stringResource(Res.string.subtitle))
        SpaceVerticalNormalView()
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
            border = BorderStroke(width = Paddings.border, color = AppColor.border)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(
                    vertical = Paddings.large, horizontal = Paddings.small
                )
            ) {
                TextSecondaryView(stringResource(Res.string.send))
                SpaceVerticalXSmallView()
                DropdownTextFieldView(
                    initialText = viewModel.fromAmount,
                    initialItem = uiState.fromCurrency.toDropDown(),
                    items = uiState.currencies,
                    onValueChange = viewModel::changeFromAmount,
                    onItemChange = viewModel::changeFromCurrency
                )
                SpaceVerticalNormalView()
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextSecondaryView(stringResource(Res.string.balance), fontSize = TextSize.small)
                    TextPrimaryView(uiState.balanceCurrent, fontSize = TextSize.small)
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextSecondaryView(
                        stringResource(Res.string.available_balance),
                        fontSize = TextSize.small
                    )
                    TextPrimaryView(uiState.balanceAvailable, fontSize = TextSize.small)
                }
                SpaceVerticalNormalView()
                TextSecondaryView(stringResource(Res.string.receive))
                SpaceVerticalXSmallView()
                DropdownTextFieldView(
                    initialText = viewModel.toAmount,
                    initialItem = uiState.toCurrency.toDropDown(),
                    items = uiState.currencies,
                    onValueChange = viewModel::changeToAmount,
                    onItemChange = viewModel::changeToCurrency
                )
                SpaceVerticalNormalView()
                TextSecondaryView(
                    stringResource(Res.string.rate),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = TextSize.small,
                    textAlign = TextAlign.Center
                )
                TextPrimaryView(
                    uiState.estimatedRate,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = TextSize.small,
                    textAlign = TextAlign.Center
                )
                SpaceVerticalNormalView()
                TextTertiaryView(stringResource(Res.string.rate_desc))
                SpaceVerticalNormalView()
                TextPrimaryView(stringResource(Res.string.options))
                SpaceVerticalXSmallView()
                TransferRadioView(onValueChange = viewModel::changeTransferOption)
                SpaceVerticalNormalView()
                ExpandableTextFieldView(
                    initialText = viewModel.purpose,
                    options = uiState.purposeOptions,
                    onValueChange = viewModel::changePurpose
                )
                SpaceVerticalSmallView()
                DefaultTextFieldView(
                    initialText = viewModel.purposePayment,
                    maxLength = MaxLength.purposePayment,
                    placeholder = stringResource(Res.string.purpose_payment),
                    onValueChange = viewModel::changePaymentPurpose
                )
                SpaceVerticalNormalView()
                TextSecondaryView(stringResource(Res.string.doc))
                SpaceVerticalXSmallView()
                UploadDocumentComponent(modifier = Modifier.fillMaxWidth())
                SpaceVerticalSmallView()
                TextTertiaryView(
                    stringResource(Res.string.doc_upload_desc),
                    modifier = Modifier.padding(horizontal = Paddings.border)
                )
            }
        }
        SpaceVerticalNormalView()
        CheckboxView(stringResource(Res.string.save))
        SpaceVerticalSmallView()
        HorizontalDivider(color = AppColor.border)
        SpaceVerticalSmallView()
        CheckboxView(buildAnnotatedString {
            append(stringResource(Res.string.agree))
            withLink(
                LinkAnnotation.Url(
                    url = uiState.link,
                    TextLinkStyles(SpanStyle(color = Color.Blue))
                )
            ) {
                append(stringResource(Res.string.read_more))
            }
        })
        SpaceVerticalNormalView()
        Button(
            onClick = viewModel::transfer,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(ShapeSize.cornerRadius),
            colors = ButtonDefaults.buttonColors(containerColor = AppColor.button)
        ) {
            Text(
                stringResource(Res.string.button_send).uppercase(),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = TextSize.large,
                color = AppColor.buttonTextColor,
            )
        }
        Spacer(Modifier.height(bottomSpace))
    }
}