package com.example.myapplication


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.component.upload.UploadDocumentComponentViewModel
import com.example.myapplication.resources.Poppins_Medium
import com.example.myapplication.resources.Poppins_Regular
import com.example.myapplication.resources.Poppins_SemiBold
import com.example.myapplication.resources.Res
import com.example.myapplication.screen.DashboardScreen
import com.example.myapplication.screen.DashboardScreenViewModel
import com.example.myapplication.theme.AppColor
import com.example.myapplication.theme.TextSize
import org.jetbrains.compose.resources.Font
import org.koin.compose.KoinApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

@Composable
fun ComposeApp() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            background = Color.White,
            onBackground = AppColor.textDefault,
            surface = Color.White,
            onSurface = AppColor.textDefault,
        ),
        typography = Typography(
            titleLarge = TextStyle(
                fontSize = TextSize.normal,
                fontFamily = FontFamily(Font(Res.font.Poppins_SemiBold)),
                fontWeight = FontWeight.SemiBold
            ),
            bodyMedium = TextStyle(
                fontSize = TextSize.normal,
                fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                fontWeight = FontWeight.Normal
            ),
            bodyLarge = TextStyle(
                fontSize = TextSize.small,
                fontFamily = FontFamily(Font(Res.font.Poppins_Medium)),
                fontWeight = FontWeight.Medium
            )
        )
    ) {
        KoinApplication(
            application = {
                modules(viewModelModule)
            }
        ) {
            DashboardScreen()
        }
    }
}

private val viewModelModule = module {
    viewModelOf(::DashboardScreenViewModel)
    viewModelOf(::UploadDocumentComponentViewModel)
}

